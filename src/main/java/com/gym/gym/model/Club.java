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

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setHeureOuverture(Date heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public void setHeureFermeture(Date heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getHeureOuverture() {
        return heureOuverture;
    }

    public Date getHeureFermeture() {
        return heureFermeture;
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public List<User> getUsers() {
        return users;
    }

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
