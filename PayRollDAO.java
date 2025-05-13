package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PayrollDAO {

    public void addPayroll(double salary, double bonus, String payDate, int absentDays, double hourRate, Employee employee) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Step 1: Create Payroll Object
            Payroll payroll = new Payroll();
            payroll.setSalary(salary);
            payroll.setBonus(bonus);
            payroll.setPayDate(payDate);
            payroll.setAbsentDays(absentDays);
            payroll.setHourRate(hourRate);

            // Associate with employee
            payroll.setEmployee(employee);

            // Step 2: Persist to DB
            session.persist(payroll);

            tx.commit();
            System.out.println("✅ Payroll added: " + payroll);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
        
            // Re-fetch employee in the same session
            Employee emp = session.find(Employee.class, 1);
        
            if (emp != null) {
                Payroll payroll = new Payroll();
                payroll.setSalary(25000);
                payroll.setBonus(3000);
                payroll.setPayDate("2025-04-30");
                payroll.setAbsentDays(2);
                payroll.setHourRate(150.0);
        
                emp.addPayroll(payroll);  // No LazyInitializationException
        
                session.merge(emp);
                tx.commit();
                System.out.println("✅ Payroll added for employee ID: " + emp.getEmpId());
            }
        }
        
    }
    
    
}
