package com.example;

import jakarta.persistence.*;

@Entity
public class Payroll {
    @Id
    @GeneratedValue
    private int payId;

    private double salary;
    private double bonus;
    private String payDate;
    private int absentDays;
    private double hourRate;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    }

    public double getHourRate() {
        return hourRate;
    }

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    // Custom Logic
    public double getHourlyRate() {
        return (salary + bonus) / 160.0;
    }

    @Override
    public String toString() {
        return "PayID: " + payId + " | Salary: " + salary + " | Bonus: " + bonus + " | PayDate: " + payDate;
    }

    public void setEmployee(Employee employee2) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'setEmployee'");
        this.employee = employee2;
    }

    // All getter & setter methods...
}
