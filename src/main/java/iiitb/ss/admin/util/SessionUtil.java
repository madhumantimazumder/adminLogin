package iiitb.ss.admin.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import iiitb.ss.admin.bean.Employee;
import iiitb.ss.admin.bean.Courses;
import iiitb.ss.admin.bean.Department;
import iiitb.ss.admin.bean.Schedule;
import iiitb.ss.admin.bean.Specialization;




public class SessionUtil {
    private static final SessionFactory sessionFactory;

    static {
        Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Specialization.class);
            configuration.addAnnotatedClass(Schedule.class);
            configuration.addAnnotatedClass(Courses.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}

