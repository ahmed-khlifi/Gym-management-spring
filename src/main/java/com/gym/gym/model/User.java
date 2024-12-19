package com.gym.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Role getRole() {
        return role;
    }

    @Column(nullable = false, length = 255, unique = true)
    private String username;  // Add the username property here

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }


    public String getPrenom() {
        return prenom;
    }



    public Abonnement getAbonnement() {
        return abonnement;
    }



    public String getEmail() {
        return email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String nom;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relation avec la table Coach
    @OneToOne(mappedBy = "user")
    private Coach coach;

    // Relation avec la table Abonnement
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Abonnement abonnement;

    // relation avec la table Club
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    /**
     * @Desscription: Check if user membership is active
     * @return "Active" or "Expired"
     */
    public String isMemebershipActive() {
        boolean active = this.abonnement.getDateFin().after(new java.util.Date());
        return active ? "Active" : "Expired";
    }

    /**
     * Get user ful name
     */
    public String getFullName() {
        return this.nom + " " + this.prenom;
    }


}
