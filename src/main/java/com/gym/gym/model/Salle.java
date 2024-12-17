package com.gym.gym.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private int capacite;

    // Relation avec la table Club
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    // Relation avec la table Cours
    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private List<Cours> cours;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}


