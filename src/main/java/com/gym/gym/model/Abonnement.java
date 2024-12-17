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

    public Date getDateFin() {
        return dateFin;
    }

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false)
    private float prix;

    // relation avec la table ModelAbonnement
    @ManyToOne
    @JoinColumn(name = "model_abonnement_id", nullable = false)
    private ModelAbonnement modelAbonnement;

    public User getUser() {
        return user;
    }

    public ModelAbonnement getModelAbonnement() {
        return modelAbonnement;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setModelAbonnement(ModelAbonnement modelAbonnement) {
        this.modelAbonnement = modelAbonnement;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public float getPrix() {
        return prix;
    }

    public int getDuree() {
        return duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Long getId() {
        return id;
    }

    // Relation avec la table User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
