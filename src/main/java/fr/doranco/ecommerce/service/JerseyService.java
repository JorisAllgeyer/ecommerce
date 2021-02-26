package fr.doranco.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.doranco.ecommerce.control.IArticlePanier;
import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.dao.ArticlePanierDAO;
import fr.doranco.ecommerce.model.dao.IArticlePanierDAO;
import fr.doranco.ecommerce.model.dao.IUtilisateurDAO;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;

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
	// @RolesAllowed({"WS"})
	@PermitAll
	@GET
	@Path("emails-panier")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public List<String> getUtilisateursAvecPanierNonVide() {
		
		List<String> emailList = new ArrayList<String>();
		
		try {
			IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
			List<Utilisateur> userList = articlePanierDAO.getUsers();
			
			userList.forEach(user -> emailList.add(user.getEmail()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emailList;
	}

}
