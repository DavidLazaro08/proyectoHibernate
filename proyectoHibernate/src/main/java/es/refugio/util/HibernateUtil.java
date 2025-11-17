package es.refugio.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/* Clase basada en el esquema de configuración mostrado en los ejemplos. */


public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure();
        return cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
