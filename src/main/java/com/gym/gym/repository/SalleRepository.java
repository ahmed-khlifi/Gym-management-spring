package com.gym.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {

    List<Salle> findByClubId(Long clubId);

}
