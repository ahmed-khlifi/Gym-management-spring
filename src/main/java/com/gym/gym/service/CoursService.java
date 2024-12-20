package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.Cours;
import com.gym.gym.repository.CoursRepository;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Cours findById(Long id) {
        return coursRepository.findById(id).get();
    }

    // save
    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public List<Object[]> findCoursesByClubIdForCurrentWeek(Long clubId) {
        return coursRepository.findCoursesByClubIdForCurrentWeek(clubId);
    }

    public long countAllCourses() {
        return coursRepository.count();
    }

}
