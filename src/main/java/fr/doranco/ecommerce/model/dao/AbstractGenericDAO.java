package fr.doranco.ecommerce.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import fr.doranco.ecommerce.model.connector.HibernateConnector;

public abstract class AbstractGenericDAO<T> implements IGenericDAO<T> {
	
	private final Session session;
	
	public AbstractGenericDAO() {
		session = HibernateConnector.getInstance().getSession();
	}

	@Override
	public T get(Class<T> entity, Integer id) throws Exception {
		return session.get( (Class<T>) entity, id);			
	}

	@Override
	public T add(T entity) throws Exception {
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
			return entity;
			
		} catch (ConstraintViolationException e) {
			tx.rollback();
			e.printStackTrace();
			throw new DuplicateEntryExcpetion(e.getMessage(), e.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		}
	}

	@Override
	public void update(T entity) throws Exception {
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public void remove(T entity) throws Exception {
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.remove(entity);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		}
	}
}
