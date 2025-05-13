package com.example;
import org.hibernate.*;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            // Create Employee
            Employee emp = new Employee();
            emp.setName("Chai");
            emp.setDepartment("Finance");
            emp.setPosition("Manager");
            emp.setStartDate("2024-01-01");

            // Create Payroll
            Payroll pay = new Payroll();
            pay.setSalary(30000);
            pay.setBonus(5000);
            pay.setPayDate("2024-04-30");

            emp.addPayroll(pay);

            // Save
            session.persist(emp);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print List
        session = factory.openSession();
        List<Employee> list = session.createQuery("from Employee", Employee.class).list();
        for (Employee e : list) {
            System.out.println("Employee: " + e);
            for (Payroll p : e.getPayrolls()) {
                System.out.println("  -> Payroll: " + p);
            }
        }

        session.close();
        factory.close();
    }
}
