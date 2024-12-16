package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.model.Cours;
import com.gym.gym.service.ClubService;
import com.gym.gym.service.CoachService;
import com.gym.gym.service.CoursService;
import com.gym.gym.service.SalleService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/cours")
public class CourseController {

    private final CoursService coursService;
    private final CoachService caocheService;
    private final ClubService clubService;
    private final SalleService salleService;

    @Autowired
    public CourseController(CoachService caocheService, ClubService clubService, SalleService salleService,
            CoursService coursService) {
        this.coursService = coursService;
        this.caocheService = caocheService;
        this.clubService = clubService;
        this.salleService = salleService;
    }

    @GetMapping("/create")
    public String createCours(Model model,
            @RequestParam(value = "step", defaultValue = "1") int step,
            @RequestParam(value = "clubId", required = false) Long clubId) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("coaches", this.caocheService.findAll());
        model.addAttribute("clubs", this.clubService.findAll());
        model.addAttribute("step", step);
        if (clubId != null) {
            model.addAttribute("salles", salleService.findByClubId(clubId));
        }
        return "add_course";
    }

    @PostMapping("/create")
    public String CreateCours(@ModelAttribute Cours cours) {
        this.coursService.save(cours);
        return "redirect:/";
    }

}
