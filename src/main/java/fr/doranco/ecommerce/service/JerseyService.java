package fr.doranco.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.dao.ArticlePanierDAO;
import fr.doranco.ecommerce.model.dao.IArticlePanierDAO;

@Path("service")
@Produces(MediaType.TEXT_PLAIN +  ";charset=UTF8")
public class JerseyService implements IJerseyService {
	
	private static final String CHARSET = ";charset=UTF8";
	
	@Override
	@GET
	@Path("ping")
	@PermitAll
	public String ping() {
		return "pong";
	}

	@Override
	@RolesAllowed({"WS"})
	@GET
	@Path("emails-panier")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Map<String, Map<Article, Integer>> getUtilisateursAvecPanierNonVide() {
		
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		// Emails and Panier
		Map<String, Map<Article, Integer>> emailsMap = new HashMap<String, Map<Article, Integer>>();
		
		try {
			List<Utilisateur> userList = articlePanierDAO.getUsers();
			
			// Loop for users
			userList.forEach(user -> {
				Map<Article, Integer> articleAndQty = new HashMap<Article, Integer>();
				
				// Loop for articles
				user.getPanier().forEach(articlePanier -> {
					Article article = articlePanier.getArticle();
					Integer qty = articlePanier.getQuantite();
					articleAndQty.put(article, qty);
				});
				
				emailsMap.put(user.getEmail(), articleAndQty);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emailsMap;
	}
	
	@Override
	@RolesAllowed({"WS"})
	@GET
	@Path("emails-list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public List<String> getEmailsUtilisateursAvecPanierNonVide() {
		
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		List<String> emailsList = new ArrayList<String>();
		
		try {
			List<Utilisateur> userList = articlePanierDAO.getUsers();
			userList.forEach(user -> emailsList.add(user.getEmail()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emailsList;
	}

}
