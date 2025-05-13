import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.entity.Employee;
import com.example.entity.Payroll;
import com.example.dao.EmployeeDAO;
import com.example.dao.PayrollDAO;

public class MainApp {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Existing Author-Book data
            Author author = new Author();
            author.setAuthorId(1);
            author.setName("George Orwell");
            author.setBio("British writer and journalist, known for 1984 and Animal Farm.");
            Book book1 = new Book();
            book1.setBookId(101);
            book1.setTitle("1984");
            book1.setPublishYear(1949);
            Book book2 = new Book();
            book2.setBookId(102);
            book2.setTitle("Animal Farm");
            book2.setPublishYear(1945);
            author.setBooks(Arrays.asList(book1, book2));
            book1.setAuthor(author);
            book2.setAuthor(author);
            session.persist(author);

            // Existing Student-Course-Enrollment data
            Student student = new Student();
            student.setStudentId(1);
            student.setName("Sara Lee");
            student.setEmail("sara.lee@example.com");
            Course course = new Course();
            course.setCourseId(1001);
            course.setCourseName("Database Systems");
            course.setCredits(3);
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentId(5001);
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            session.persist(student);

            // Existing User-Profile data
            User user = new User();
            user.setUserId(1);
            user.setUsername("noon_sky");
            user.setEmail("noon@example.com");
            Profile profile = new Profile();
            profile.setProfileId(101);
            profile.setAge(25);
            profile.setGender("Female");
            user.setProfile(profile);
            profile.setUser(user);
            session.persist(user);

            // New Employee and Payroll data using DAO
            EmployeeDAO empDAO = new EmployeeDAO();
            PayrollDAO payDAO = new PayrollDAO();
            Employee employee = new Employee();
            employee.setEmpId(1);
            employee.setEmpName("John Doe");
            employee.setEmpPos("Developer");
            empDAO.save(employee);

            Payroll payroll = new Payroll();
            payroll.setEmpId(1); // Link to employee
            payroll.setMonth(5);
            payroll.setHourRate(20.0);
            payroll.setHoursWorked(160);
            payroll.setTotalPay(payroll.getHourRate() * payroll.getHoursWorked());
            payDAO.save(payroll);

            tx.commit();
            System.out.println("✅ Data inserted successfully for all entities.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
