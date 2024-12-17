package com.gym.gym.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.Coach;
import com.gym.gym.repository.CoachRepository;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    // Récupérer la liste de tous les coaches
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    // Récupérer un coach par son ID
    public Coach findById(Long id) {
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.orElse(null); // Retourne le coach ou null s'il n'est pas trouvé
    }

    // Sauvegarder un coach (ajouter ou mettre à jour)
    public Coach save(Coach coach) {
        return coachRepository.save(coach); // Persiste le coach dans la base de données
    }

    // Supprimer un coach par son ID
    public void delete(Long id) {
        coachRepository.deleteById(id); // Supprime le coach par son ID
    }
}