package fr.doranco.ecommerce.control;

import fr.doranco.ecommerce.entity.Commande;

public interface ICommande {
	Commande addCommande(Commande commande) throws Exception;
}
