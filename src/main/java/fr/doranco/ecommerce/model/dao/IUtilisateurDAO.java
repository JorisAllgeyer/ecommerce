package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.Utilisateur;

public interface IUtilisateurDAO<T> extends IGenericDAO<T> {
	List<Utilisateur> getUtilisateurs();
	List<Utilisateur> getEmployes();
	
	Utilisateur getUtilisateurByEmail(String email);
}
