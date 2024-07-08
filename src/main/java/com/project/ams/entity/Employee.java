package com.project.ams.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer employeeId; // 社員ID (Employee ID)

    @Column(nullable = false)
    private String employeeName; // 氏名 (Name)

    private LocalDate employeeJoiningDate; // 入社年月日 (Date of Joining)

    @Enumerated(EnumType.STRING)
    private Gender employeeGender; // 性別 (Gender)

    private LocalDate employeeBirthDate; // 生年月日 (Date of Birth)

    private String employeeDepartment; // 所属 (Department)

    private String employeeEmail; // メールアドレス (Email Address)

    // age update on register, restart server and daily base
    private Integer employeeAge; // 年齢

    private enum Gender{男, 女, その他} // 性別enum定義


    public Integer getId() {
        return id;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public LocalDate getEmployeeBirthDate() {
        return employeeBirthDate;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public LocalDate getEmployeeJoiningDate() {
        return employeeJoiningDate;
    }

    public void setEmployeeJoiningDate(LocalDate employeeJoiningDate) {
        this.employeeJoiningDate = employeeJoiningDate;
    }

    public Gender getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Gender employeeGender) {
        this.employeeGender = employeeGender;
    }

    public void setEmployeeBirthDate(LocalDate employeeBirthDate) {
        this.employeeBirthDate = employeeBirthDate;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }
}