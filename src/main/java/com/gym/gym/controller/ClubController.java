package com.gym.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.service.ClubService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/all")
    public String getAllClub(Model model) {
        model.addAttribute("clubs", clubService.findAll());
        return "club_list";
    }

    @GetMapping("/create")
    public String createClub() {
        return new String();
    }

    @PostMapping("/create")
    public String postMethodName() {
        return new String();
    }

    @GetMapping("/details/{id}")
    public String getClubDetails(@RequestParam(value = "id") Long id) {
        return new String();
    }
}
