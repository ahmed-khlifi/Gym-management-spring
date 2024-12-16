package com.gym.gym.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false, name = "heure_overture")
    @Temporal(TemporalType.TIME)
    private Date heureOuverture;

    @Column(nullable = false, name = "heure_fermeture")
    @Temporal(TemporalType.TIME)
    private Date heureFermeture;

    // Relation avec la table Salle
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Salle> salles;

    // Relation avec la table User
    @OneToMany(mappedBy = "club")
    private List<User> users;

    @Override
    public String toString() {
        return nom + " - " + adresse;
    }
}
