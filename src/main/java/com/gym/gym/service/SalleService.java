package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.Salle;
import com.gym.gym.repository.SalleRepository;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> findAll() {
        return salleRepository.findAll();
    }

    public List<Salle> findByClubId(Long id) {
        return salleRepository.findByClubId(id);
    }

    public Salle findById(Long id) {
        return salleRepository.findById(id).orElse(null); // Handle null safely
    }

    public Salle save(Salle salle) {
        return salleRepository.save(salle);
    }

    public void deleteById(Long id) {
        salleRepository.deleteById(id);
    }
}