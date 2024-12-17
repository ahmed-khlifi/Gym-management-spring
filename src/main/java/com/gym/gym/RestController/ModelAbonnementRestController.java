package com.gym.gym.RestController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.gym.model.ModelAbonnement;
import com.gym.gym.service.ModelAbonnementService;

@RestController
@RequestMapping("/api/model-abonnements")
public class ModelAbonnementRestController {

    @Autowired
    private ModelAbonnementService modelAbonnementService;

    // Get all abonnements
    @GetMapping
    public ResponseEntity<List<ModelAbonnement>> getAllModelAbonnements() {
        List<ModelAbonnement> modelAbonnementList = modelAbonnementService.findAll();
        return ResponseEntity.ok(modelAbonnementList);
    }

    // Get a specific abonnement by ID
    @GetMapping("/{id}")
    public ResponseEntity<ModelAbonnement> getModelAbonnementById(@PathVariable Long id) {
        try {
            ModelAbonnement modelAbonnement = modelAbonnementService.findById(id);
            return ResponseEntity.ok(modelAbonnement);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new abonnement
    @PostMapping
    public ResponseEntity<ModelAbonnement> createModelAbonnement(@RequestBody ModelAbonnement modelAbonnement) {
        ModelAbonnement savedModelAbonnement = modelAbonnementService.save(modelAbonnement);
        return ResponseEntity.ok(savedModelAbonnement);
    }

    // Update an existing abonnement
    @PutMapping("/{id}")
    public ResponseEntity<ModelAbonnement> updateModelAbonnement(
            @PathVariable Long id,
            @RequestBody ModelAbonnement updatedModelAbonnement
    ) {
        try {
            ModelAbonnement existingModelAbonnement = modelAbonnementService.findById(id);
            updatedModelAbonnement.setId(id); // Ensure the ID matches
            ModelAbonnement savedModelAbonnement = modelAbonnementService.save(updatedModelAbonnement);
            return ResponseEntity.ok(savedModelAbonnement);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an abonnement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModelAbonnement(@PathVariable Long id) {
        try {
            ModelAbonnement modelAbonnement = modelAbonnementService.findById(id);
            modelAbonnementService.deleteById(id); // Assuming the deleteById method exists
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
