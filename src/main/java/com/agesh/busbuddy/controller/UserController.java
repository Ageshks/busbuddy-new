package com.agesh.busbuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.agesh.busbuddy.service.UserService;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.dto.userDto;



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password) {
        if (userService.authenticate(email, password)) {
            return "redirect:/user-dashboard";
        }
        return "login";
    }

    @GetMapping("/super-admin-page")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String superAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "super-admin";
    }

    // Add other mappings for roles like admin and user page
}

