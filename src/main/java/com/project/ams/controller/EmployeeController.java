package com.project.ams.controller;

import com.project.ams.entity.Employee;
import com.project.ams.exception.*;
import com.project.ams.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Method to handle the initial page load (static HTML view)
    @GetMapping("")
    public String showEmployeePage() {
        return "employee";
    }

    @GetMapping("/api/employees")
    @ResponseBody
    public ResponseEntity<Page<Employee>> getEmployeeList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "employeeId", required = false) Integer employeeId,
            @RequestParam(value = "joiningDateFrom", required = false) LocalDate joiningDateFrom,
            @RequestParam(value = "joiningDateTo", required = false) LocalDate joiningDateTo,
            @RequestParam(value = "department", required = false) String department) {

        Pageable pageable = PageRequest.of(page - 1, rows, Sort.by("employeeId"));
        Page<Employee> employeePage = employeeService.getEmployeeByFilters(employeeId, joiningDateFrom, joiningDateTo, department, pageable);

        return ResponseEntity.ok(employeePage);
    }

    // Delete employees
    @PostMapping({"/delete"})
    public String deleteEmployee(@RequestParam("deleteEmployeeIds") List<Integer> deleteEmployeeIds){
        for (Integer i : deleteEmployeeIds) {
            employeeService.deleteEmployeeByEmployeeId(i);
        }
        return "redirect:/employee";
    }

    // link to employeeRegister page
    @GetMapping("/registration")
    public String employeeRegistration(Model model){
        model.addAttribute("employeeId", employeeService.findLargestEmployeeId());
        return "employeeRegister";
    }

    // Register a new employee
    @PostMapping("/register")
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

    // link to edit employee page
    @GetMapping("/{employeeId}")
    public String employeeInfo(@PathVariable("employeeId") Integer employeeId, Model model){
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        model.addAttribute("employee", employee);
        return "employeeEdit";
    }

    // edit an employee
    @PostMapping("/edit")
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
