package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    
}
