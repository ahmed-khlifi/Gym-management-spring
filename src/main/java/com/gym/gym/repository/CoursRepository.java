package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    
}
