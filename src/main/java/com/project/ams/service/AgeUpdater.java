package com.project.ams.service;

import com.project.ams.dao.EmployeeDao;
import com.project.ams.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class AgeUpdater {

    @Autowired
    private EmployeeDao employeeDao;

    @PostConstruct
    public void updateAgesOnStartup() {
        List<Employee> employees = employeeDao.findAll();
        for (Employee employee : employees) {
            employee.setEmployeeAge(calculateAge(employee.getEmployeeBirthDate()));
        }
        employeeDao.saveAll(employees);
    }

    private Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) return null;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
