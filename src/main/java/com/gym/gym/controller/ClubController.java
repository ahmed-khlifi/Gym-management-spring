package com.gym.gym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.gym.gym.model.Club;
import com.gym.gym.service.ClubService;
import com.gym.gym.service.CoursService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;
    private CoursService coursService;

    @Autowired
    public ClubController(ClubService clubService, CoursService coursService) {
        this.clubService = clubService;
        this.coursService = coursService;
    }

    @GetMapping("/all")
    public String getAllClub(Model model) {
        model.addAttribute("clubs", clubService.findAll());
        return "club_list";
    }

    @GetMapping("/create")
    public String createClub(Model model) {
        model.addAttribute("club", new Club());
        return "add_club";
    }

    @PostMapping("/create")
    public String createClub(Model model, @ModelAttribute Club club) {
        clubService.save(club);
        return "redirect:/club/all";
    }

    @GetMapping("/details/{id}")
    public String getClubDetails(Model model, @PathVariable("id") Long id) {
        Club club = clubService.findById(id);
        List<Object[]> coursesData = coursService.findCoursesByClubIdForCurrentWeek(id);
        model.addAttribute("club", club);
        model.addAttribute("title", club.getNom());
        // map through the list of courses
        List<Map<String, Object>> courses = new ArrayList<>();
        for (Object[] row : coursesData) {
            Map<String, Object> course = new HashMap<>();
            course.put("id", row[0]);
            course.put("nom", row[8]);
            course.put("description", row[2]);
            course.put("duree", row[4]);
            course.put("heure_debut", row[5]);
            course.put("heure_fin", row[6]);
            course.put("jour", row[7]);
            course.put("coach", row[9]);
            course.put("nom_jour", row[row.length - 1]);
            courses.add(course);
        }
        model.addAttribute("cours", courses);

        return "club_details";
    }
}
