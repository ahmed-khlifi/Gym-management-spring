package com.gym.gym.controller;

import com.gym.gym.model.Coach;
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
    public String getAllCoaches(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<Coach> coachPage = coachService.findAll(PageRequest.of(page, size));
        model.addAttribute("coaches", coachPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coachPage.getTotalPages());
        model.addAttribute("totalItems", coachPage.getTotalElements());
        return "list-coaches";
    }

    // Get details of a single coach
    @GetMapping("/{id}")
    public String getCoachDetails(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        return "view-coach";
    }

    // Form to edit a coach's price
    @GetMapping("/{id}/edit")
    public String editCoachPrice(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        return "edit-coach-price";
    }

    @GetMapping("/add")
    public String showAddCoachForm(Model model) {
        model.addAttribute("coach", new Coach());
        model.addAttribute("users", userService.findAll()); // Fetch all users
        return "add-coach";
    }

    @PostMapping("/add")
    public String addCoach(@ModelAttribute Coach coach, Model model) {
        if (coachService.isUserAlreadyACoach(coach.getUser().getId())){
            model.addAttribute("errorMessage", "This user is already a coach.");
            return "add-coach";
        }
        coachService.saveCoach(coach);
        return "redirect:/coaches";
    }

    // Get a coach by User ID
    @GetMapping("/{userId}")
    public Coach getCoachByUserId(@PathVariable Long userId) {
        return coachService.findById(userId);
    }

    // Get coaches by price range
    @GetMapping("/price-range")
    public List<Coach> getCoachesByPriceRange(@RequestParam float minPrice, @RequestParam float maxPrice) {
        return coachService.findCoachesByPriceRange(minPrice, maxPrice);
    }

    // Get coaches by course ID
    @GetMapping("/course/{courseId}")
    public List<Coach> getCoachesByCourse(@PathVariable Long courseId) {
        return coachService.findCoachesByCourseId(courseId);
    }

    // Get coaches with no assigned courses
    @GetMapping("/no-courses")
    public List<Coach> getCoachesWithNoCourses() {
        return coachService.findCoachesWithNoCourses();
    }

    // Update coach's price
    @PostMapping("/{id}/price")
    public String updateCoachPrice(@PathVariable Long id, @RequestParam float prixCours) {
        coachService.updateCoachPrice(id, prixCours);
        return "redirect:/coaches";
    }
}
