package com.project.ams.service;

import com.project.ams.dao.EmployeeDao;
import com.project.ams.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class DailyAgeUpdater {

    @Autowired
    private EmployeeDao employeeDao;

    private static final Logger logger = LoggerFactory.getLogger(DailyAgeUpdater.class);

    @Scheduled(cron = "0 30 15 * * ?") // Every day at 15:30
    public void updateAgesDaily() {
        List<Employee> employees = employeeDao.findAll();
        for (Employee employee : employees) {
            employee.setEmployeeAge(calculateAge(employee.getEmployeeBirthDate()));
        }
        employeeDao.saveAll(employees);
        System.out.println("Employee ages updated at " + LocalDate.now());
    }

    private Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) return null;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
