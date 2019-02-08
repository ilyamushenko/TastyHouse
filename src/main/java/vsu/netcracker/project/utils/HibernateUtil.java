package vsu.netcracker.project.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import vsu.netcracker.project.database.models.*;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory concreteSessionFactory;

    static {
        try {
            Properties prop= new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/TastyHouse");
            prop.setProperty("hibernate.connection.username", "postgres");
            prop.setProperty("hibernate.connection.password", "0000");
            prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

            concreteSessionFactory = new Configuration().addProperties(prop)
                    .addAnnotatedClass(DishesFromOrder.class)
                    .addAnnotatedClass(Dishes.class)
                    .addAnnotatedClass(Orders.class)
                    .addAnnotatedClass(RoleStaff.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Statuses.class)
                    .addAnnotatedClass(TypeDish.class)
                    .addAnnotatedClass(TypePayment.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return concreteSessionFactory.openSession();
    }
}
