package com.gym.gym.RestController;

import com.gym.gym.model.Abonnement;
import com.gym.gym.model.ModelAbonnement;
import com.gym.gym.model.User;
import com.gym.gym.repository.AbonnementRepository;
import com.gym.gym.service.AbonnementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gym.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/abonnement")
public class AbonnementController {

    private static final Logger logger = LoggerFactory.getLogger(AbonnementController.class);

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private UserService userService;

    @Autowired
    private AbonnementRepository abonnementRepository;

    /**
     * Get an abonnement by ID.
     *
     * @param id The ID of the abonnement.
     * @return ResponseEntity with the abonnement data.
     */

    @GetMapping("/{id}")
    public ResponseEntity<Abonnement> getAbonnement(@PathVariable Long id) {
        logger.info("Received request to get abonnement with id: {}", id);

        Optional<Abonnement> abonnement = abonnementRepository.findById(id);
        if (abonnement.isPresent()) {
            logger.info("Abonnement found: {}", abonnement.get());
            return ResponseEntity.ok(abonnement.get());
        } else {
            logger.warn("Abonnement with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    /**
     * Create a new abonnement.
     *
     * @param userId The user ID associated with the abonnement.
     * @param planId The plan ID (ModelAbonnement).
     * @param period The subscription period (in months).
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping("/createAbonnement")
    public ResponseEntity<String> createAbonnement(
            @RequestParam Long userId,
            @RequestParam Long planId,
            @RequestParam int period) {

        try {
            // Find user and model abonnement (plan)
            User user = userService.findById(userId);
            Optional<Abonnement> modelAbonnement = abonnementRepository.findById(planId);  // Use ModelAbonnementRepository

            // Check if both user and model abonnement are found
            if (user != null && modelAbonnement.isPresent()) {
                abonnementService.save(user, modelAbonnement.get().getModelAbonnement(), period);  // Use .get() to retrieve the ModelAbonnement object
                return ResponseEntity.status(HttpStatus.CREATED).body("Abonnement created successfully.");
            } else {
                // Return error if user or abonnement is not found
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or ModelAbonnement not found.");
            }
        } catch (Exception e) {
            // Catch any unexpected exceptions and log the error
            e.printStackTrace();  // Optionally log this using a logger (e.g., SLF4J)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }

    }
    /**
     * Update an existing abonnement.
     *
     * @param id The ID of the abonnement to update.
     * @param planId The new plan ID (ModelAbonnement).
     * @param period The new subscription period.
     * @return ResponseEntity indicating success or failure.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAbonnement(
            @PathVariable Long id,
            @RequestParam Long planId,
            @RequestParam int period) {

        try {
            abonnementService.updateAbonnementUser(id, planId, period);
            return ResponseEntity.status(HttpStatus.OK).body("Abonnement updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

