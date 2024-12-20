package com.gym.gym.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gym.gym.model.Coach;
import com.gym.gym.repository.CoachRepository;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Page<Coach> findAll(Pageable pageable) {
        return coachRepository.findAll(pageable);
    }

    public Coach findById(Long userId) {
        return coachRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Coach not found for user ID: " + userId));
    }

    // by price
    public List<Coach> findCoachesByPriceRange(float minPrice, float maxPrice) {
        return coachRepository.findByPrixCoursBetween(minPrice, maxPrice);
    }

    // by courses
    public List<Coach> findCoachesByCourseId(Long courseId) {
        return coachRepository.findCoachesByCourseId(courseId);
    }

    // without courses
    public List<Coach> findCoachesWithNoCourses() {
        return coachRepository.findCoachesWithNoCourses();
    }

    public int updateCoachPrice(Long id, float prixCours) {
        return coachRepository.updateCoachPrice(id, prixCours);
    }

    @Transactional
    public void saveCoach(Coach coach) {
        Optional<Coach> existingCoach = coachRepository.findByUser_Id(coach.getUser().getId());
        if (existingCoach.isPresent()) {
            throw new IllegalStateException("This user is already a coach.");
        }
        coachRepository.save(coach);
    }

    public boolean isUserAlreadyACoach(Long userId) {
        // return false;
        return coachRepository.findByUser_Id(userId).isPresent();
    }

    public long countAllCoaches() {
        return coachRepository.count();
    }

}
