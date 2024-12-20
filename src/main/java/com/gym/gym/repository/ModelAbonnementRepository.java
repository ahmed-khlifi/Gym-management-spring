package com.gym.gym.repository;

import com.gym.gym.model.ModelAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModelAbonnementRepository extends JpaRepository<ModelAbonnement, Long> {
    List<ModelAbonnement> findByType(String type);
}
