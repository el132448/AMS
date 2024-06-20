package com.project.ams.service;

import com.project.ams.dao.EmployeeDao;
import com.project.ams.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployee(){
        return employeeDao.findAll();
    }

}
