package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeeDAO {

    public void deleteEmployeeById(int empId) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Step 1: Find
            Employee emp = session.find(Employee.class, empId);

            if (emp != null) {
                // Step 2: Delete
                session.remove(emp);  // ✅ Hibernate 6+

                System.out.println("✅ Employee deleted: " + empId);
            } else {
                System.out.println("⚠️ Employee not found: " + empId);
            }

            // Step 3: Commit
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public void addEmployee(String name, String department, String position, String startDate) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Step 1: Create Object
            Employee employee = new Employee();
            employee.setName(name);
            employee.setDepartment(department);
            employee.setPosition(position);
            employee.setStartDate(startDate);

            // Step 2: Save Object
            session.persist(employee);  // Use persist() or save()

            tx.commit();
            System.out.println("✅ Employee added: " + employee);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public void listAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Step 1: Create Query (HQL)
            String hql = "FROM Employee"; // HQL uses class name, not table name
            List<Employee> employees = session.createQuery(hql, Employee.class).list();

            // Step 2: Process Results
            if (employees.isEmpty()) {
                System.out.println("⚠️ No employees found.");
            } else {
                for (Employee emp : employees) {
                    System.out.println("🧑‍💼 " + emp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Employee getEmployeeById(int id) {
        Employee employee = null;
    
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // ✅ Hibernate 6: Use session.find instead of session.get
            employee = session.find(Employee.class, id);
    
            if (employee == null) {
                System.out.println("⚠️ Employee ID " + id + " not found.");
            } else {
                System.out.println("✅ Found employee: " + employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return employee;
    }
    
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // Example data
        // dao.addEmployee("Nok", "IT", "Developer", "2025-04-01");
        // dao.addEmployee("Bee", "HR", "Officer", "2025-01-15");

        // List all employees
        dao.listAllEmployees();

        // EmployeeDAO dao = new EmployeeDAO();
        // dao.deleteEmployeeById(2); // change 3 to the target empId
    }
 
}
