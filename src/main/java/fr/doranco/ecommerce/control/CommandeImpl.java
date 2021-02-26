package fr.doranco.ecommerce.control;

import fr.doranco.ecommerce.entity.Commande;
import fr.doranco.ecommerce.model.dao.CommandeDAO;
import fr.doranco.ecommerce.model.dao.ICommandeDAO;
import fr.doranco.ecommerce.utils.OrderUtils;

public class CommandeImpl implements ICommande {

	@Override
	public Commande addCommande(Commande commande) throws Exception {
		commande.setNumero(OrderUtils.generateOrderNumber(commande.getUtilisateur().getId()));
		ICommandeDAO<Commande> commandeDAO = new CommandeDAO();
		return commandeDAO.add(commande);
	}

}
