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

    public void deleteEmployeeById(Integer id){
        employeeDao.deleteById(id);
    }

    public Employee getEmployeeById(Integer id){
        if (employeeDao.findById(id).isPresent()){
            return employeeDao.findById(id).get();
        }else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    public void addEmployee(Employee employee){
        System.out.println(employee.getId());
        employeeDao.save(employee);
        System.out.println(employee.getId());
    }

    public Long findLargestEmployeeId(){
        return employeeDao.findLargestEmployeeId() + 1 ;
    }

}

class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}