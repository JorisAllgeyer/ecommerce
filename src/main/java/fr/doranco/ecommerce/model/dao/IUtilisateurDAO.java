package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.Utilisateur;

public interface IUtilisateurDAO<T> extends IGenericDAO<T> {
	List<Utilisateur> getUtilisateurs() throws Exception;
	List<Utilisateur> getEmployes() throws Exception;
	
	Utilisateur getUtilisateurByEmail(String email) throws Exception;
}
