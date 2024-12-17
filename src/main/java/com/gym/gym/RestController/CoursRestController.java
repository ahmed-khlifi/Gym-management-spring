package com.gym.gym.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.gym.model.Cours;
import com.gym.gym.service.CoursService;

@RestController
@RequestMapping("/api/cours")
public class CoursRestController {

    @Autowired
    private CoursService coursService;

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Cours>> getAllCours() {
        List<Cours> coursList = coursService.findAll();
        return ResponseEntity.ok(coursList);
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable Long id) {
        Cours cours = coursService.findById(id);
        if (cours != null) {
            return ResponseEntity.ok(cours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) {
        Cours savedCours = coursService.save(cours);
        return ResponseEntity.ok(savedCours);
    }

    // Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Cours> updateCours(@PathVariable Long id, @RequestBody Cours updatedCours) {
        Cours existingCours = coursService.findById(id);
        if (existingCours != null) {
            updatedCours.setId(id); // Assurez-vous que l'ID correspond
            Cours savedCours = coursService.save(updatedCours);
            return ResponseEntity.ok(savedCours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        Cours cours = coursService.findById(id);
        if (cours != null) {
            coursService.deleteById(id); // Supposons que vous avez une méthode deleteById dans le service
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



