package com.gym.gym.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.ModelAbonnement;
import com.gym.gym.repository.ModelAbonnementRepository;

@Service
public class ModelAbonnementService {

    @Autowired
    private ModelAbonnementRepository modelAbonnementRepository;

    public List<ModelAbonnement> findAll() {
        return modelAbonnementRepository.findAll();
    }

    public ModelAbonnement findById(Long id) {
        Optional<ModelAbonnement> modelAbonnement = modelAbonnementRepository.findById(id);
        if (modelAbonnement.isPresent()) {
            return modelAbonnement.get();
        } else {
            throw new RuntimeException("ModelAbonnement with ID " + id + " not found");
        }
    }

    public List<ModelAbonnement> findByType(String type) {
        return modelAbonnementRepository.findByType(type);
    }
}
