package com.gym.gym.RestController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.gym.model.Salle;
import com.gym.gym.service.SalleService;

@RestController
@RequestMapping("/api/salles")
public class SalleRestController {

    @Autowired
    private SalleService salleService;

    // Get all salles
    @GetMapping
    public ResponseEntity<List<Salle>> getAllSalles() {
        List<Salle> salleList = salleService.findAll();
        return ResponseEntity.ok(salleList);
    }

    // Get a specific salle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        try {
            Salle salle = salleService.findById(id);
            return ResponseEntity.ok(salle);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get salles by Club ID
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Salle>> getSallesByClubId(@PathVariable Long clubId) {
        List<Salle> salleList = salleService.findByClubId(clubId);
        return ResponseEntity.ok(salleList);
    }

    // Create a new salle
    @PostMapping
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        Salle savedSalle = salleService.save(salle);
        return ResponseEntity.ok(savedSalle);
    }

    // Update an existing salle
    @PutMapping("/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable Long id, @RequestBody Salle updatedSalle) {
        try {
            Salle existingSalle = salleService.findById(id);
            updatedSalle.setId(id); // Ensure the ID matches
            Salle savedSalle = salleService.save(updatedSalle);
            return ResponseEntity.ok(savedSalle);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a salle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        try {
            Salle salle = salleService.findById(id);
            salleService.deleteById(id); // Assuming the deleteById method exists
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
