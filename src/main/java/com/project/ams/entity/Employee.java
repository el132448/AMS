package com.project.ams.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 社員ID (Employee ID)
    private String joiningDate; // 入社年月日 (Date of Joining)
    private String name; // 氏名 (Name)
    private String gender; // 性別 (Gender)
    private String birthDate; // 生年月日 (Date of Birth)
    @Transient // Not a column in table
    private int age; // 年齢
    private String department; // 所属 (Department)
    private String email; // メールアドレス (Email Address)

//    public void calculateAge() {
//        if (this.birthDate != null) {
//            this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
//        } else {
//            this.age = 0;
//        }
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}