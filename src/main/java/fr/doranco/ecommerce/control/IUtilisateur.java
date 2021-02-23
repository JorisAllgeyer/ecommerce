package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Utilisateur;

public interface IUtilisateur {
// READ
	List<Utilisateur> getUtilisateurs();
	List<Utilisateur> getEmployes();
	
// WRITE
	Utilisateur addUtilisateur(Utilisateur utilisateur, String password) throws Exception;
	void updateUtilisateur(Utilisateur utilisateur) throws Exception;
	void removeUtilisateur(Utilisateur utilisateur) throws Exception;

// CONNECTION
	Utilisateur connect(String email, String password);
}
