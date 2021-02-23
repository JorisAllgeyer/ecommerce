package fr.doranco.ecommerce.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.doranco.ecommerce.model.connector.HibernateConnector;


public abstract class AbstractGenericDAO<T> implements IGenericDAO<T> {

	@Override
	public T get(Class<T> entity, Integer id) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			return session.get( (Class<T>) entity, id);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (session != null) session.close();
		}
	}

	@Override
	public T add(T entity) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
			return entity;		
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) session.close();
		}
	}

	@Override
	public void update(T entity) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) session.close();
		}
		
	}

	@Override
	public void remove(T entity) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.remove(entity);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) session.close();
		}
	}
}
