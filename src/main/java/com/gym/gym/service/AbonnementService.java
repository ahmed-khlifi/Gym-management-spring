package com.gym.gym.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.model.Abonnement;
import com.gym.gym.model.ModelAbonnement;
import com.gym.gym.model.User;
import com.gym.gym.repository.AbonnementRepository;
import com.gym.gym.repository.ModelAbonnementRepository;

@Service
public class AbonnementService {
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private ModelAbonnementRepository modelAbonnementRepository;

    public void save(User user, ModelAbonnement modelAbonnement, int period) {
        Abonnement abonnement = new Abonnement();
        abonnement.setUser(user);
        abonnement.setModelAbonnement(modelAbonnement);
        abonnement.setDuree(period);
        abonnement.setPrix(modelAbonnement.getPrix() * period);
        abonnement.setDateDebut(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, period);
        Date dateFin = calendar.getTime();
        abonnement.setDateFin(dateFin);

        abonnementRepository.save(abonnement);
    }

    /**
     * Update user subscription, change plan and period
     * steps:
     * - get the subscription by id
     * - update the plan and period
     * - update the price
     * - update the end date
     * - save the subscription
     * 
     */
    public void updateAbonnementUser(Long id, Long planId, int period) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));

        // plan is the same and period is different
        // update the period and the price ( comulative price )
        if (abonnement.getModelAbonnement().getId() == planId && abonnement.getDuree() != period) {
            abonnement.setDuree(period);
            // set the new price
            float newPrice = (abonnement.getModelAbonnement().getPrix() * period) + abonnement.getPrix();
            abonnement.setPrix(newPrice);
        }
        // plan is different
        // update the plan, period and the price
        else if (abonnement.getModelAbonnement().getId() != planId) {
            // get new plan and affect it to the subscription
            ModelAbonnement modelAbonnement = modelAbonnementRepository.findById(planId)
                    .orElseThrow(() -> new RuntimeException("ModelAbonnement not found"));

            abonnement.setModelAbonnement(modelAbonnement);
            abonnement.setDuree(period);

            float newPrice = (modelAbonnement.getPrix() * period) + abonnement.getPrix();
            abonnement.setPrix(newPrice);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, period);
        Date dateFin = calendar.getTime();
        abonnement.setDateFin(dateFin);
        abonnementRepository.save(abonnement);
    }
}
