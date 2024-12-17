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

    public Long getId() {
        return id;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public User getUser() {
        return user;
    }

    public float getPrixCours() {
        return prixCours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrixCours(float prixCours) {
        this.prixCours = prixCours;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
