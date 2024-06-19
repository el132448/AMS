package com.project.ams.controller;

import com.project.ams.entity.User;
import com.project.ams.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    // add attribute "user" to model for every response of this controllers
    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(User user){
        return "registration";
    }

    @PostMapping("registration")
    public String submitRegistrationForm(@ModelAttribute("user") User user){
        userService.registerNewUser(user);
        return "redirect:/registration?success";
    }
}
