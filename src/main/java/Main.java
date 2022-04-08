import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

        Employee employee = new Employee(null,"hassan mohseni","mohseni@gmail.com",30000L);
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(employee);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
            }

        }
        Session session1=sessionFactory.openSession();
        Employee emp1=(Employee)session1.load(Employee.class,1);
        System.out.println(emp1.getId()+" "+emp1.getFullName()+" "+emp1.getSalary());
        session1.close();

        Session session2=sessionFactory.openSession();
        Employee emp2=(Employee)session2.load(Employee.class,1);
        System.out.println(emp2.getId()+" "+emp2.getFullName()+" "+emp2.getSalary());
        session2.close();
    }
}
