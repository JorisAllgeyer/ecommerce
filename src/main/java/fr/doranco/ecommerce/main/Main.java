package fr.doranco.ecommerce.main;

import fr.doranco.ecommerce.entity.Adresse;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Genre;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;
import fr.doranco.ecommerce.utils.Dates;

public class Main {
	
	public static void main(String[] args) {
		
		Adresse adresse = new Adresse();
		
		adresse.setNumero(Integer.parseInt("42"));
		adresse.setRue("Rue des pyrénées");
		adresse.setVille("Paris");
		adresse.setCodePostal("codePostal");
		
		System.out.println(adresse);
	}

}
