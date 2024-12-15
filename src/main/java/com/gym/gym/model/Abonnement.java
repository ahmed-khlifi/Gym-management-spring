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
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(nullable = false, name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false)
    private float prix;

    // relation avec la table ModelAbonnement
    @ManyToOne
    @JoinColumn(name = "model_abonnement_id", nullable = false)
    private ModelAbonnement modelAbonnement;

    // Relation avec la table User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
