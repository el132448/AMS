package com.project.ams.dao;

import com.project.ams.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    // Custom query to find the largest employee id
    @Query(value = "SELECT MAX(e.id) FROM Employee e")
    Long findLargestEmployeeId();
}
