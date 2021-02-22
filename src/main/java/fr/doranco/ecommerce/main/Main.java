package fr.doranco.ecommerce.main;

import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Genre;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;
import fr.doranco.ecommerce.utils.Dates;

public class Main {

	public static void main(String[] args) {
		
		Utilisateur user = new Utilisateur();
		
		user.setNom("Allgeyer");
		user.setPrenom("Joris");
		user.setGenre(Genre.MONSIEUR.getGenre());
		user.setDateNaissance(Dates.stringToDate("20/10/1993"));
		user.setRole(Role.ADMIN.getRole());
		user.setTelephone("0606060606");
		user.setEmail("joris@joris.com");
		user.setActif(true);
		
		UtilisateurDAO userDAO = new UtilisateurDAO();
		
		try {
			Utilisateur userAdded = userDAO.add(user);
			
			Integer userId = userAdded.getId();
			Utilisateur userGet = userDAO.get(Utilisateur.class, userId);
			
			System.out.println(userGet);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
