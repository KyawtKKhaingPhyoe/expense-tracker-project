package com.dev.db;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.dev.entity.Expense;
import com.dev.entity.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

			Properties properties = new Properties();

			properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/expense_tracker_db");
			properties.put(Environment.USER, "root");
			properties.put(Environment.PASS, "adminadmin");
			properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
			properties.put(Environment.HBM2DDL_AUTO, "update");
			properties.put(Environment.SHOW_SQL, true);

			configuration.setProperties(properties);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Expense.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}

		return sessionFactory;
	}

}



//public class HibernateUtil {
//
//	public static SessionFactory getSessionFactory() {
//
//		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//
//		return sessionFactory;
//	}
//
//}
