package com.gym.gym.service;

import java.util.List;
import java.util.Optional;

import com.gym.gym.model.Coach;
import com.gym.gym.repository.CoachRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.ModelAbonnementEnum;
import com.gym.gym.model.Role;
import com.gym.gym.model.User;
import com.gym.gym.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private CoachService coachService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AbonnementService abonnementService;
    @Autowired
    private ModelAbonnementService modelAbonnement;
    @Autowired
    private CoachRepository coachRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<User> getUsersByMembershipTypeAndName(ModelAbonnementEnum type, String name) {
        return userRepository.getUsersByMembershipTypeAndName(type, name);
    }

    @Transactional
    public void saveCoach(User user) {
        user.setRole(Role.COACH);
        User newUser = userRepository.save(user);
        Coach coach = new Coach();
        coach.setUser(newUser);
        coachService.saveCoach(coach);
        System.out.println("User saved with ID: " + user.getId());
    }

    public void save(User user, Long planId, int period) {
        user.setRole(Role.USER);
        User newUser = userRepository.save(user);
        this.abonnementService.save(
                newUser,
                this.modelAbonnement.findById(planId),
                period);
    }

    public User update(Long id, User userDetails, Long planId, int period) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDetails.getEmail());
        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        // handle plan update
        if (planId != user.getAbonnement().getModelAbonnement().getId() || period != user.getAbonnement().getDuree()) {
            this.abonnementService.updateAbonnementUser(user.getAbonnement().getId(), planId, period);
        }
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isUserAlreadyACoach(User user) {
        Optional<Coach> coach = coachRepository.findByUser_Id(user.getId());
        if (coach.isPresent()) {
            return true; // User is already a coach
        }
        return user.getRole() == Role.COACH;
    }

    public long countAllUsers() {
        return userRepository.count();
    }


}
