package fr.doranco.ecommerce.model.dao;

public interface IGenericDAO<T> {
	
	T get(Class<T> entity, Integer id) throws Exception;
	T add(T entity) throws Exception;
	void update(T entity) throws Exception;
	void remove(T entity) throws Exception;
}
