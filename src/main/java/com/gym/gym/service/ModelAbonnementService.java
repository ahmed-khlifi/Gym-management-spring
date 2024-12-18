package com.gym.gym.service;

import java.util.List;

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
        return modelAbonnementRepository.findById(id).get();
    }
}
