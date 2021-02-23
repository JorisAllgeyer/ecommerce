package fr.doranco.ecommerce.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.connector.HibernateConnector;

public class UtilisateurDAO 
	extends AbstractGenericDAO<Utilisateur> 
	implements IUtilisateurDAO<Utilisateur> {
	
	@Override
	public List<Utilisateur> getUtilisateurs() {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("Utilisateur.findAll");
			return (List<Utilisateur>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}
	
	@Override
	public List<Utilisateur> getEmployes() {
		Session session = HibernateConnector.getInstance().getSession();
		try {
			Query query = session.createNamedQuery("Utilisateur.findAllEmployes");
			query.setParameter("role", "Client");
			return (List<Utilisateur>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}	

	@Override
	public Utilisateur getUtilisateurByEmail(String email) {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("Utilisateur.findByEmail");
			query.setParameter("email", email);
			return (Utilisateur) query.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}
}
