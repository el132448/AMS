package com.project.ams.controller;

import com.project.ams.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping({"/","/employee"})
    public String showAllEmployee(Model model){
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employee";
    }

    @PostMapping({"deleteEmployee"})
    public String deleteEmployee(@RequestParam("employeeIds") List<String> employeeIds){
        for (String i : employeeIds) {
            System.out.println(i);
        }

        return "redirect:/employee";
    }

}
