package fr.doranco.ecommerce.model.connector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {
	
	// Sessions and transactions
	// https://developer.jboss.org/docs/DOC-13951
	// Try method: getCurrentSession(), auto-close connections
	
	private static HibernateConnector instance;
	private static SessionFactory sessionFactory;
	
	private HibernateConnector() throws HibernateException {
		if (sessionFactory == null) sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static synchronized HibernateConnector getInstance() throws HibernateException {
		if (instance == null) instance = new HibernateConnector();
		return instance;
	}
	
	public Session getSession() throws HibernateException {
		Session session = sessionFactory.openSession();
		
		if (!session.isConnected()) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	public static void shutDown() throws HibernateException {
		sessionFactory.close();
	}

}
