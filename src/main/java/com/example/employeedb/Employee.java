package com.example.employeedb;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
    Create a new Spring Boot application for Employees and Departments. Each Employee can work
    in only one Department. Each Department can have many Employees. Your application should be
    able to add, update and delete employees and also departments.


 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=3)
    private String employeeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
    private Department department;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
