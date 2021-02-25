package fr.doranco.ecommerce.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.connector.HibernateConnector;

public class ArticlePanierDAO 
	extends AbstractGenericDAO<ArticlePanier> 
	implements IArticlePanierDAO<ArticlePanier> {

	@Override
	public List<ArticlePanier> getArticlesPanier() throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("ArticlePanier.findAll");
			return (List<ArticlePanier>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
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
		} finally {
			if (session != null) session.close();
		}
		return null;
	}

	@Override
	public ArticlePanier getArticlePanierByUserAndArticle(Utilisateur user, Article article) throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		
		try {
			Query query = session.createNamedQuery("ArticlePanier.findByUserAndArticle");
			query.setParameter("user", user);
			query.setParameter("article", article);
			return (ArticlePanier) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}

}
