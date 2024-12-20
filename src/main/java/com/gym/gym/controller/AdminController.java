package com.gym.gym.controller;

import com.gym.gym.service.CoachService;
import com.gym.gym.service.CoursService;
import com.gym.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CoachService coachService;

    @Autowired
    private CoursService coursService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("totalUsers", userService.countAllUsers());
        model.addAttribute("totalCoaches", coachService.countAllCoaches());
        model.addAttribute("totalCourses", coursService.countAllCourses());
        return "dashboard-admin";
    }


}
