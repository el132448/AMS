package com.project.ams.service;

import com.project.ams.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class EmployeeSpecifications {

    public static Specification<Employee> withEmployeeId(Integer employeeId) {
        return (root, query, criteriaBuilder) ->
                employeeId != null ? criteriaBuilder.equal(root.get("employeeId"), employeeId) : null;
    }

    public static Specification<Employee> withJoiningDateRange(LocalDate joiningDateFrom, LocalDate joiningDateTo) {
        return (root, query, criteriaBuilder) -> {
            if (joiningDateFrom != null && joiningDateTo != null) {
                return criteriaBuilder.between(root.get("employeeJoiningDate"), joiningDateFrom, joiningDateTo);
            } else if (joiningDateFrom != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("employeeJoiningDate"), joiningDateFrom);
            } else if (joiningDateTo != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("employeeJoiningDate"), joiningDateTo);
            }
            return null;
        };
    }

    public static Specification<Employee> withDepartment(String department) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(department) ? criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeDepartment")), "%" + department.toLowerCase() + "%") : null;
    }
}
