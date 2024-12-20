package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.gym.model.Abonnement;
import com.gym.gym.model.User;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

    List<Abonnement> findByUser(User user);

    List<Abonnement> findByModelAbonnement_Id(Long modelAbonnementId);

    List<Abonnement> findByDateFinBefore(java.util.Date dateFin);

    List<Abonnement> findByDateDebutAfter(java.util.Date dateDebut);

    Abonnement findByIdAndUser(Long id, User user);
}
