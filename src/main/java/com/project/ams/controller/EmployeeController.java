package com.project.ams.controller;

import com.project.ams.entity.Employee;
import com.project.ams.entity.User;
import com.project.ams.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    public String deleteEmployee(@RequestParam("employeeIds") List<Integer> employeeIds){
        for (Integer i : employeeIds) {
            employeeService.deleteEmployeeById(i);
        }
        return "redirect:/employee";
    }

    // Register employee
    @GetMapping("/employee/registration")
    public String employeeRegistration(Model model){
        System.out.println(employeeService.findLargestEmployeeId());
        model.addAttribute("employeeId", employeeService.findLargestEmployeeId());
        System.out.println(employeeService.findLargestEmployeeId());
        return "employeeRegister";
    }

    // Edit employee
    @GetMapping("/employee/{id}")
    public String employeeInfo(@PathVariable("id") Integer id, Model model){
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employeeEdit";
    }

    @PostMapping("editEmployee")
    public String editEmployee(@ModelAttribute("employee") Employee employee){
        System.out.println(employee.getId());
        employeeService.addEmployee(employee);
        System.out.println(employee.getId());
        return "redirect:/employee";
    }

}
