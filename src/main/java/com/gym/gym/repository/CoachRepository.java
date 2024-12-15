package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    
}
