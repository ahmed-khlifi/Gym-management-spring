package com.gym.gym.controller;

import com.gym.gym.model.Coach;
import com.gym.gym.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.gym.gym.model.ModelAbonnementEnum;
import com.gym.gym.model.Role;
import com.gym.gym.model.User;
import com.gym.gym.service.ModelAbonnementService;
import com.gym.gym.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CoachService coachService;

    // Service
    private final UserService userService;
    private final ModelAbonnementService modelAbonnement;

    @Autowired
    public UserController(
            UserService userService,
            ModelAbonnementService modelAbonnement) {
        this.userService = userService;
        this.modelAbonnement = modelAbonnement;
    }

    @GetMapping("/all")
    public String getAllMembers(
            Model model,
            @RequestParam(value = "type", required = false) ModelAbonnementEnum type,
            @RequestParam(required = false) String name) {
        model.addAttribute("users", this.userService.getUsersByMembershipTypeAndName(type, name));
        model.addAttribute("modelAbonnement", this.modelAbonnement.findAll());
        return "user_list";
    }

    @GetMapping("/details/{id}")
    public String getMember(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        return "user_details";
    }

    @GetMapping("/update/{id}")
    public String getMemberToUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        model.addAttribute("modelAbonnement", this.modelAbonnement.findAll());
        model.addAttribute("forUpdate", true);
        return "add_user";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute User user, @RequestParam("planId") Long planId,
            @RequestParam("period") int period, @RequestParam("id") Long id) {
        this.userService.update(id, user, planId, period);
        return "redirect:/user/all";
    }

    @GetMapping("/add-member")
    public String addMember(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("modelAbonnement", this.modelAbonnement.findAll());
        model.addAttribute("forUpdate", false);

        return "add_user";
    }

    @PostMapping("/add-member")
    public String addMember(@ModelAttribute User user,
                            @RequestParam("planId") Long planId,
            @RequestParam("period") int period) {
        this.userService.save(user, planId, period);
        return "redirect:/user/all";
    }

    @GetMapping("/add-coach")
    public String addCoachForm(Model model) {
        model.addAttribute("user", new User());  // empty user object for the form
        model.addAttribute("forUpdate", false);  // flag to indicate it's an add form
        return "add_coach";  // your form view
    }

    @PostMapping("/add-coach")
    public String addCoach(@ModelAttribute User user,
                           @RequestParam float prixCours,  // price for the course
                           Model model) {
        // Check if the user is already a coach
        if (userService.isUserAlreadyACoach(user)) {
            model.addAttribute("errorMessage", "This user is already a coach.");
            return "add_coach";  // return the form with an error message
        }

        // Set the role 'COACH' and save the user as a coach
        userService.saveCoach(user);

        // Create a new coach object and set the price for the course
        Coach coach = new Coach();
        coach.setUser(user);
        coach.setPrixCours(prixCours);

        // Save the coach
        coachService.saveCoach(coach);

        return "redirect:/user/all";  // redirect to the user list or a specific page
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id) {
        this.userService.deleteById(id);
        return "redirect:/user/all";
    }

}
