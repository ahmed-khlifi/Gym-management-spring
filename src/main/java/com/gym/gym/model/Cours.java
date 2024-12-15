package com.gym.gym.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float duree;

    @Column(nullable = false)
    private String niveau;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date jour;

    @Column(nullable = false, name = "heure_debut")
    @Temporal(TemporalType.TIME)
    private Date heureDebut;

    @Column(nullable = false, name = "heure_fin")
    @Temporal(TemporalType.TIME)
    private Date heureFin;

    @Column(nullable = true)
    private int capacite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CoursesCibleEnum cible;

    // Relation avec la table Salle
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    // Relation avec la table Coach
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
}
