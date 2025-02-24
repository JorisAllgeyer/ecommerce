package fr.doranco.ecommerce.control;

import fr.doranco.ecommerce.entity.Adresse;

public interface IAdresse {
// READ
	Adresse getAdresse(Integer id) throws Exception;

// WRITE	
	Adresse addAdresse(Adresse adresse) throws Exception;
	void updateAdresse(Adresse adresse) throws Exception;
	void removeAdresse(Adresse adresse) throws Exception;
}
