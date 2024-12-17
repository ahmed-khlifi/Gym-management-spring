package com.gym.gym.RestController;
import com.gym.gym.model.Club;
import com.gym.gym.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubRestController {

    @Autowired
    private ClubService clubService;

    // Récupérer tous les clubs
    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.findAll();
    }

    // Récupérer un club par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable("id") Long id) {
        try {
            Club club = clubService.findById(id);
            return new ResponseEntity<>(club, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Créer un nouveau club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        // Ajouter le club dans la base de données
        Club savedClub = clubService.saveClub(club);
        return new ResponseEntity<>(savedClub, HttpStatus.CREATED);
    }

    // Mettre à jour un club existant
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable("id") Long id, @RequestBody Club club) {
        try {
            Club updatedClub = clubService.updateClub(id, club);
            return new ResponseEntity<>(updatedClub, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un club par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable("id") Long id) {
        try {
            clubService.deleteClub(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No Content => succès sans retour
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
