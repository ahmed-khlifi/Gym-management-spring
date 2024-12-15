package com.gym.gym.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "prix_cours")
    private float prixCours;

    // Relation avec la table User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relation avec la table Cours
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Cours> cours;
}
