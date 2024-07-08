package com.project.ams.service;

import com.project.ams.dao.EmployeeDao;
import com.project.ams.entity.Employee;
import com.project.ams.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

//    @Autowired
//    private EmailService emailService;

    public Page<Employee> getEmployeeByFilters(Integer employeeId, LocalDate joiningDateFrom, LocalDate joiningDateTo, String department, Pageable pageable) {
        // Build specifications based on the filters
        Specification<Employee> spec = Specification.where(null);

        if (employeeId != null) {
            spec = spec.and(EmployeeSpecifications.withEmployeeId(employeeId));
        }
        if (joiningDateFrom != null || joiningDateTo != null) {
            spec = spec.and(EmployeeSpecifications.withJoiningDateRange(joiningDateFrom, joiningDateTo));
        }
        if (department != null && !department.isEmpty()) {
            spec = spec.and(EmployeeSpecifications.withDepartment(department));
        }

        // Execute the query with filters and pagination
        return employeeDao.findAll(spec, pageable);
    }

    @Transactional // JPA requires that UPDATE, DELETE, and INSERT operations be executed within a transaction
    public void deleteEmployeeByEmployeeId(Integer employeeId){
        employeeDao.deleteByEmployeeId(employeeId);
    }

    public Employee getEmployeeByEmployeeId(Integer employeeId){
        Employee employee = employeeDao.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with Employee ID " + employeeId + " not found");
        }
        return employee;
    }

    @Transactional(rollbackFor = DuplicateEmployeeIdException.class)
    public void registerEmployee(Employee employee) throws DuplicateEmployeeIdException {
        try {
            Integer employeeAge = Period.between(employee.getEmployeeBirthDate(), LocalDate.now()).getYears();
            employee.setEmployeeAge(employeeAge);
            employeeDao.save(employee);

//            // Send confirmation email
//            String subject = "Employee Registration Confirmation";
//            String body = "Dear " + employee.getEmployeeName() + ",\n\nYour registration is successful.\n\nRegards,\nCompany";
//            emailService.sendEmail(employee.getEmployeeEmail(), subject, body);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmployeeIdException("Employee ID already exists");
        }
    }

    public Integer findLargestEmployeeId(){
        Integer largestEmployeeId = employeeDao.findLargestEmployeeId();
        return (largestEmployeeId != null ? largestEmployeeId : 0) + 1;
    }

    @Transactional(rollbackFor = DuplicateEmployeeIdException.class)
    public void editEmployee(Employee employee) throws DuplicateEmployeeIdException {
        try {
            Integer employeeId = employee.getEmployeeId();
            Integer id = employeeDao.findIdByEmployeeId(employeeId);
            employee.setId(id);
            Integer employeeAge = Period.between(employee.getEmployeeBirthDate(), LocalDate.now()).getYears();
            employee.setEmployeeAge(employeeAge);
            employeeDao.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmployeeIdException("Employee ID already exists");
        }
    }

}
