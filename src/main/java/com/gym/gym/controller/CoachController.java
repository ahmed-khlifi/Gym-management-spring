package com.gym.gym.controller;

import com.gym.gym.model.Coach;
import com.gym.gym.model.User;
import com.gym.gym.service.CoachService;
import com.gym.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;
    @Autowired
    private UserService userService;

    // List all coaches with pagination
    @GetMapping
    public String getAllCoaches(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        if (page < 0 || size <= 0) {
            page = 0;
            size = 10;
        }

        Page<Coach> coachPage = coachService.findAll(PageRequest.of(page, size));
        model.addAttribute("coaches", coachPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coachPage.getTotalPages());
        model.addAttribute("totalItems", coachPage.getTotalElements());
        return "list-coaches";
    }

    // Get details of a single coach
    @GetMapping("/details/{id}")
    public String getCoachDetails(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        return "view-coach";
    }

    // Form to add a new coach
    @GetMapping("/add")
    public String showAddCoachForm(Model model) {
        model.addAttribute("user", new User()); // Correct attribute name
        model.addAttribute("prixCours", 0.0f); // Default price for input
        return "add-coach";
    }

    @PostMapping("/add")
    public String addCoach(@ModelAttribute User user,
                           @RequestParam float prixCours,
                           Model model) {
        // Validate if user is already a coach
        if (userService.isUserAlreadyACoach(user)) {
            model.addAttribute("errorMessage", "This user is already a coach.");
            return "add-coach";
        }

        // Save the coach using the service
        userService.saveCoach(user);
        return "redirect:/coaches";
    }

    // Edit coach's price form
    @GetMapping("/{id}/edit")
    public String editCoachPrice(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        return "edit-coach-price";
    }

    // Update coach's price
    @PostMapping("/{id}/edit")
    public String updateCoachPrice(@PathVariable Long id, @RequestParam float prixCours) {
        coachService.updateCoachPrice(id, prixCours);
        return "redirect:/coaches";
    }

    // Find coaches by price range
    @GetMapping("/price-range")
    public String getCoachesByPriceRange(@RequestParam float minPrice,
                                         @RequestParam float maxPrice,
                                         Model model) {
        List<Coach> coaches = coachService.findCoachesByPriceRange(minPrice, maxPrice);
        model.addAttribute("coaches", coaches);
        return "list-coaches";
    }

    // Find coaches with no courses assigned
    @GetMapping("/no-courses")
    public String getCoachesWithNoCourses(Model model) {
        List<Coach> coaches = coachService.findCoachesWithNoCourses();
        model.addAttribute("coaches", coaches);
        return "list-coaches";
    }
}
