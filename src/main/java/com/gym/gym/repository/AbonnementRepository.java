package com.gym.gym.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Abonnement;

import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

    @EntityGraph(attributePaths = {"modelAbonnement", "user", "user.club", "user.coach"})
    Optional<Abonnement> findById(Long id);
    
}
