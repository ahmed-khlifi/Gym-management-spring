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
}
