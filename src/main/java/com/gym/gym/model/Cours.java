package com.gym.gym.model;

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

    // Relation avec la table Salle
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    // Relation avec la table Coach
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
}
