package com.gym.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Club;

public interface ClubRepository  extends JpaRepository<Club, Long> {
    
}
