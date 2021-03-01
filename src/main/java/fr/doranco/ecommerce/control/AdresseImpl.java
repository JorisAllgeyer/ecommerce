package fr.doranco.ecommerce.control;

import fr.doranco.ecommerce.entity.Adresse;
import fr.doranco.ecommerce.model.dao.AdresseDAO;
import fr.doranco.ecommerce.model.dao.IAdresseDAO;

public class AdresseImpl implements IAdresse {
	
	@Override
	public Adresse getAdresse(Integer id) throws Exception {
		IAdresseDAO<Adresse> adresseDAO = new AdresseDAO();
		return adresseDAO.get(Adresse.class, id);
	}

	@Override
	public Adresse addAdresse(Adresse adresse) throws Exception {
		IAdresseDAO<Adresse> adresseDAO = new AdresseDAO();
		return adresseDAO.add(adresse);
	}

	@Override
	public void updateAdresse(Adresse adresse) throws Exception {
		IAdresseDAO<Adresse> adresseDAO = new AdresseDAO();
		adresseDAO.update(adresse);

	}

	@Override
	public void removeAdresse(Adresse adresse) throws Exception {
		IAdresseDAO<Adresse> adresseDAO = new AdresseDAO();
		adresseDAO.remove(adresse);
	}

}
