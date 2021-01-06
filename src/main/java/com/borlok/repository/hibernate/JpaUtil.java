package com.borlok.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class JpaUtil {
    private static SessionFactory sessionFactory;

static
    {
            sessionFactory = new AnnotationConfiguration()
                    .configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
    }

    public static void openConnectionToDatabase() {
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeAllSessions() {
        sessionFactory.close();
    }

}
