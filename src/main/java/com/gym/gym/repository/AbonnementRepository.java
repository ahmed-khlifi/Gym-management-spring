package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Abonnement;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    
}
