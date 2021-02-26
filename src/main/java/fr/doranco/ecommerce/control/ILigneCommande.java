package fr.doranco.ecommerce.control;

import fr.doranco.ecommerce.entity.LigneCommande;

public interface ILigneCommande {
	LigneCommande addLigneCommande(LigneCommande ligneCommande) throws Exception;
}
