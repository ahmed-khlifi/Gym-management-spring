package com.gym.gym.RestController;

import com.gym.gym.model.Coach;
import com.gym.gym.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coaches") // Route de base pour l'API Coach
public class CoachRestController {

    @Autowired
    private CoachService coachService;

    // GET : Récupérer tous les coaches
    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.findAll();
    }

    // GET : Récupérer un coach par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        Optional<Coach> coach = Optional.ofNullable(coachService.findById(id));
        return coach.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Ajouter un nouveau coach
    @PostMapping
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach) {
        Coach createdCoach = coachService.save(coach);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoach);
    }

    // PUT : Mettre à jour un coach existant
    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id, @RequestBody Coach coachDetails) {
        Optional<Coach> optionalCoach = Optional.ofNullable(coachService.findById(id));

        if (optionalCoach.isPresent()) {
            Coach coach = optionalCoach.get();
            coach.setPrixCours(coachDetails.getPrixCours()); // Mettre à jour le nom (et les autres attributs selon ton modèle)

            Coach updatedCoach = coachService.save(coach);
            return ResponseEntity.ok(updatedCoach);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE : Supprimer un coach par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        Optional<Coach> optionalCoach = Optional.ofNullable(coachService.findById(id));

        if (optionalCoach.isPresent()) {
            coachService.delete(id); // Supposer que tu as une méthode delete dans le service
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
