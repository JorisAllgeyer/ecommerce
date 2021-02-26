package fr.doranco.ecommerce.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.connector.HibernateConnector;

public class UtilisateurDAO 
	extends AbstractGenericDAO<Utilisateur> 
	implements IUtilisateurDAO<Utilisateur> {
	
	private final Session session;
	
	public UtilisateurDAO() {
		session = HibernateConnector.getInstance().getSession();
	}
	
	@Override
	public List<Utilisateur> getUtilisateurs() throws Exception {
		
		try {
			Query query = session.createNamedQuery("Utilisateur.findAll");
			return (List<Utilisateur>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
	}
	
	@Override
	public List<Utilisateur> getEmployes() throws Exception  {
	
		try {
			Query query = session.createNamedQuery("Utilisateur.findAllEmployes");
			query.setParameter("role", "Client");
			return (List<Utilisateur>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
	}	

	@Override
	public Utilisateur getUtilisateurByEmail(String email) throws Exception  {
		
		try {
			Query query = session.createNamedQuery("Utilisateur.findByEmail");
			query.setParameter("email", email);
			return (Utilisateur) query.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
