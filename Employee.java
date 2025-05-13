package com.example;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    private String name;
    private String department;
    private String position;
    private String startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payroll> payrolls = new ArrayList<>();

    // Getters and Setters
    public void addPayroll(Payroll p) {
        p.setEmployee(this);
        payrolls.add(p);
    }

    public void removePayroll(Payroll p) {
        payrolls.remove(p);
        p.setEmployee(null);
    }

    @Override
    public String toString() {
        return empId + " | " + name + " | " + department + " | " + position + " | " + startDate;
    }

    public List<Payroll> getPayrolls() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getPayrolls'");
        return payrolls;
    }

    // All getter & setter methods...
}
