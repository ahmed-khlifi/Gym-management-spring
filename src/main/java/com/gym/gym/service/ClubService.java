package com.gym.gym.service;

import com.gym.gym.model.Club;
import com.gym.gym.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    // Trouver tous les clubs
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    // Trouver un club par ID
    public Club findById(Long id) {
        return clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"));
    }

    // Enregistrer un nouveau club
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    // Mettre à jour un club existant
    public Club updateClub(Long id, Club clubDetails) {
        // Trouver le club à mettre à jour
        Club club = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"));

        // Mettre à jour les informations du club
        club.setNom(clubDetails.getNom());
        club.setAdresse(clubDetails.getAdresse());
        club.setTelephone(clubDetails.getTelephone());
        club.setHeureOuverture(clubDetails.getHeureOuverture());
        club.setHeureFermeture(clubDetails.getHeureFermeture());

        // Sauvegarder le club mis à jour
        return clubRepository.save(club);
    }

    // Supprimer un club
    public void deleteClub(Long id) {
        // Trouver le club à supprimer
        Club club = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"));

        // Supprimer le club
        clubRepository.delete(club);
    }
}
