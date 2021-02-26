package fr.doranco.ecommerce.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.connector.HibernateConnector;

public class ArticlePanierDAO 
	extends AbstractGenericDAO<ArticlePanier> 
	implements IArticlePanierDAO<ArticlePanier> {
	
	private final Session session;
	
	public ArticlePanierDAO() {
		session = HibernateConnector.getInstance().getSession();
	}

	@Override
	public List<ArticlePanier> getArticlesPanier() throws Exception {
		
		try {
			Query query = session.createNamedQuery("ArticlePanier.findAll");
			return (List<ArticlePanier>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public List<ArticlePanier> getArticlesPanierByUser(Utilisateur user) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("ArticlePanier.findByUser");
			query.setParameter("user", user);
			return (List<ArticlePanier>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
	}

	@Override
	public ArticlePanier getArticlePanierByUserAndArticle(Utilisateur user, Article article) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("ArticlePanier.findByUserAndArticle");
			query.setParameter("user", user);
			query.setParameter("article", article);
			List<ArticlePanier> articlePanier = query.getResultList();
			
			if (articlePanier.isEmpty()) 
				return null;			
			
			return articlePanier.get(0);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
	}

	@Override
	public void cleanPanier(Utilisateur user) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query query = session.createNamedQuery("ArticlePanier.cleanUserPanier");
			query.setParameter("user", user);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new Exception(e);
		} 
	}

	@Override
	public List<Utilisateur> getUsers() throws Exception {
		try {
			Query query = session.createNamedQuery("ArticlePanier.getUsers");
			return (List<Utilisateur>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
