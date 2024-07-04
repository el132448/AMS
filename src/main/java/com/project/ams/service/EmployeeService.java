package com.project.ams.service;

import com.project.ams.dao.EmployeeDao;
import com.project.ams.entity.Employee;
import com.project.ams.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployee(){
        return employeeDao.findAll();
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
            employeeDao.save(employee);
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
            employeeDao.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmployeeIdException("Employee ID already exists");
        }
    }

}
