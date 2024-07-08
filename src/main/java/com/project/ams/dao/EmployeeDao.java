package com.project.ams.dao;

import com.project.ams.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    // Custom query to find the largest employee id
    @Query(value = "SELECT MAX(e.employeeId) FROM Employee e")
    Integer findLargestEmployeeId();

    // Find employee by employeeId
    Employee findByEmployeeId(Integer employeeId);

    // Custom query to delete employee by employee id
    @Modifying  // necessary when modify the database (such as INSERT, UPDATE, or DELETE)
    @Query("DELETE FROM Employee e WHERE e.employeeId = :employeeId")
    void deleteByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query(value = "SELECT e.id FROM Employee e WHERE e.employeeId = :employeeId")
    Integer findIdByEmployeeId(@Param("employeeId") Integer employeeId);

    // The findByPaging method is redundant; use findAll with Specification and Pageable
    //    @Query("FROM Employee e")
    //    public Page<Employee> findByPaging(Pageable pageable);
    /* List<Employee> vs Page<Employee>:
    Pagination Metadata:
    total number of pages
    , total number of elements
    , current page number
    , and size of the page
    */

}
