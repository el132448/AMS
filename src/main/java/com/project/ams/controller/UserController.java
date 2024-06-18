package com.project.ams.controller;

import com.project.ams.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/","/users"})
    public String users(){
        return "users";
    }

}
