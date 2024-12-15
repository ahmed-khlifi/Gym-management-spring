package com.gym.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Home Page");
        model.addAttribute("message", "Welcome to Your Spring Project!");
        return "home"; // Refers to src/main/resources/templates/home.html
    }
}
