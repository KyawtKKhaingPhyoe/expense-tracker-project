package com.dev.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dev.entity.User;

public class UserDao {

	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;

	public UserDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public boolean saveUser(User user) {

		boolean f = false;

		try {
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(user);
			transaction.commit();
			
			f = true;
			
		} catch (Exception e) {
			
			if (transaction != null) {
				f = false;
				e.printStackTrace();
			}
			
		}

		return f;

	}
	
	public User login(String email,String password) {
		
		User user = null;
		
		session = sessionFactory.openSession();
		
		Query q = session.createQuery("from User where email=:em and password=:pass");
		
		q.setParameter("em", email);
		q.setParameter("pass", password);
		
		user = (User) q.uniqueResult();
		
		return user;
		
	}

}
