package com.gym.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/club")
public class ClubController {

    @GetMapping("/all")
    public String getAllClub() {
        return new String();
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
