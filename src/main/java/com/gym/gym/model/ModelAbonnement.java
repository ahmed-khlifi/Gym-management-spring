package com.gym.gym.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "model_abonnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ModelAbonnementEnum getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public float getPrix() {
        return prix;
    }

    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModelAbonnementEnum type;

    @Column(nullable = false)
    private float prix;

    // Relation avec la table Abonnement
    @OneToMany(mappedBy = "modelAbonnement", cascade = CascadeType.ALL)
    private List<Abonnement> abonnements = new ArrayList<>();

}
