package com.gym.gym.controller;

import com.gym.gym.model.Abonnement;
import com.gym.gym.model.ModelAbonnement;
import com.gym.gym.model.User;
import com.gym.gym.service.AbonnementService;
import com.gym.gym.service.ModelAbonnementService;
import com.gym.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelAbonnementService modelAbonnementService;

    @GetMapping("/subscriptions/add")
    public String showAddAbonnementForm(Model model) {
        model.addAttribute("users", userService.findAll()); // Liste des utilisateurs
        model.addAttribute("plans", modelAbonnementService.findAll()); // Liste des plans d'abonnement
        return "add_abonnement";
    }

    @PostMapping("/subscriptions/add")
    public String addAbonnement(Long user_id, Long model_abonnement_id, int period) {
        User user = userService.findById(user_id);

        ModelAbonnement modelAbonnement = modelAbonnementService.findById(model_abonnement_id);

        abonnementService.save(user, modelAbonnement, period);

        return "redirect:/subscriptions"; 
    }
}
