package fr.doranco.ecommerce.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.connector.HibernateConnector;
import fr.doranco.ecommerce.model.dao.ArticleDAO;
import fr.doranco.ecommerce.model.dao.IArticleDAO;
import fr.doranco.ecommerce.model.dao.IUtilisateurDAO;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;

public class Main {
	
	public static void main(String[] args) {
		
		Session session = HibernateConnector.getInstance().getSession();		
		
		try {
			IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
			IArticleDAO<Article> articleDAO = new ArticleDAO();
			
			Utilisateur user = userDAO.get(Utilisateur.class, 34);
			Article article = articleDAO.get(Article.class, 4);
			
			Query query = session.createNamedQuery("ArticlePanier.findByUser");
			query.setParameter("user", user);
			// query.setParameter("article", article);
			
			List<ArticlePanier> result = (List<ArticlePanier>) query.list();
			
			System.out.println("result -> " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

}
