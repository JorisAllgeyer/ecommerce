package fr.doranco.ecommerce.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Genre;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.model.connector.HibernateConnector;
import fr.doranco.ecommerce.model.dao.ArticleDAO;
import fr.doranco.ecommerce.model.dao.IArticleDAO;
import fr.doranco.ecommerce.model.dao.IUtilisateurDAO;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;
import fr.doranco.ecommerce.utils.Dates;

public class Main {
	
	public static void main(String[] args) {
		
		Session session = HibernateConnector.getInstance().getSession();		
		
		try {
//			IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
//			Utilisateur user = new Utilisateur("ALLGEYER", "Joris", Genre.MONSIEUR.getGenre(), 
//					Dates.stringToDate("20/10/1993"), Role.ADMIN.getRole(), "0606060606", 
//					joris@admin.com, password, cleCryptage, isActif);
			// userDAO.add(user);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

}
