package com.gym.gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.model.Coach;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    Optional<Coach> findByUser_Id(Long userId);

    // Find coaches by price range
    List<Coach> findByPrixCoursBetween(float minPrice, float maxPrice);

    // Find coaches by courses
    @Query("SELECT c FROM Coach c JOIN c.cours co WHERE co.id = :courseId")
    List<Coach> findCoachesByCourseId(@Param("courseId") Long courseId);

    Page<Coach> findAll(Pageable pageable);

    // coaches don't have courses
    @Query("SELECT c FROM Coach c WHERE c.cours IS EMPTY")
    List<Coach> findCoachesWithNoCourses();

    // update price de coach
    @Modifying
    @Transactional
    @Query("UPDATE Coach c SET c.prixCours = :prixCours WHERE c.id = :id")
    int updateCoachPrice(@Param("id") Long id, @Param("prixCours") float prixCours);

    // Optional<Coach> findByUsersId(Long userId);
}
