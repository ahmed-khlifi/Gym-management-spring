package com.gym.gym.model;

import java.util.Date;
import java.util.Calendar;

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

    @ManyToOne
    @JoinColumn(name = "model_abonnement_id", nullable = false)
    private ModelAbonnement modelAbonnement;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public void calculerDateFin() {
        if (this.dateDebut != null && this.duree > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.dateDebut);
            calendar.add(Calendar.MONTH, this.duree);
            this.dateFin = calendar.getTime();
        }
    }
}
