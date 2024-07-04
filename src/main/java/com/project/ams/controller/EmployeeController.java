package com.project.ams.controller;

import com.project.ams.entity.Employee;
import com.project.ams.exception.*;
import com.project.ams.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Select all employees
    @GetMapping({"/","/employee"})
    public String showAllEmployee(Model model){
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employee";
    }

    // Delete employee
    @PostMapping({"deleteEmployee"})
    public String deleteEmployee(@RequestParam("deleteEmployeeIds") List<Integer> deleteEmployeeIds){
        for (Integer i : deleteEmployeeIds) {
            employeeService.deleteEmployeeByEmployeeId(i);
        }
        return "redirect:/employee";
    }

    // Register employee
    @GetMapping("/employee/registration")
    public String employeeRegistration(Model model){
        model.addAttribute("employeeId", employeeService.findLargestEmployeeId());
        return "employeeRegister";
    }

    @PostMapping("registerEmployee")
    public String registerEmployee(@ModelAttribute("employee") Employee employee, Model model){
        try {
            employeeService.registerEmployee(employee);
            return "redirect:/employee";
        }catch (DuplicateEmployeeIdException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("employee", employee);
            return "employeeRegister";
        }
    }

    // Edit employee
    @GetMapping("/employee/{employeeId}")
    public String employeeInfo(@PathVariable("employeeId") Integer employeeId, Model model){
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        model.addAttribute("employee", employee);
        return "employeeEdit";
    }

    @PostMapping("editEmployee")
    public String editEmployee(@ModelAttribute("employee") Employee employee, Model model, RedirectAttributes redirectAttributes){
        try {
            employeeService.editEmployee(employee);
            redirectAttributes.addAttribute("employeeId", employee.getEmployeeId());
            return "redirect:/employee/{employeeId}?success";
        }catch (DuplicateEmployeeIdException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("employee", employee);
            return "employeeEdit";
        }
    }

}
