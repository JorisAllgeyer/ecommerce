package fr.doranco.ecommerce.control;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.dao.IUtilisateurDAO;
import fr.doranco.ecommerce.model.dao.UtilisateurDAO;
import fr.doranco.ecommerce.cryptage.CryptageDES;
import fr.doranco.ecommerce.cryptage.GenerateKey;
import fr.doranco.ecommerce.cryptage.AlgoCryptage;

public class UtilisateurImpl implements IUtilisateur {
	
	@Override
	public List<Utilisateur> getUtilisateurs() {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		return userDAO.getUtilisateurs();
	}

	@Override
	public List<Utilisateur> getEmployes() {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		return userDAO.getEmployes();
	}
	
	@Override
	public Utilisateur getUtilisateurByEmail(String email) {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		return userDAO.getUtilisateurByEmail(email);
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur user, String password) throws Exception {
		
		// Format Names
		user.setNom(user.getNom().trim().toUpperCase());
		user.setPrenom(user.getPrenom().trim().substring(0, 1).toUpperCase()
			.concat(user.getPrenom().trim().substring(1).toLowerCase()));
		
		try {
			SecretKey passwordKey = GenerateKey.getKey(AlgoCryptage.DES.getAlgo(), 56);
			byte[] passwordEncrypt = CryptageDES.encrypt(password, passwordKey);
			
			user.setPassword(passwordEncrypt);
			user.setCleCryptage(passwordKey.getEncoded());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		return userDAO.add(user);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws Exception {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		userDAO.update(utilisateur);
	}

	@Override
	public void removeUtilisateur(Utilisateur utilisateur) throws Exception {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		userDAO.remove(utilisateur);
	}

	@Override
	public Utilisateur connect(String email, String password) {
		IUtilisateurDAO<Utilisateur> userDAO = new UtilisateurDAO();
		
		try {
			Utilisateur utilisateur = userDAO.getUtilisateurByEmail(email);
			
			byte[] passwordFromSQL = utilisateur.getPassword();
			byte[] key = utilisateur.getCleCryptage();
			SecretKey secretKey = new SecretKeySpec(key, AlgoCryptage.DES.getAlgo());
			
			String passwordFromSQLDecrypted = CryptageDES.decrypt(passwordFromSQL, secretKey);
			if (password.equals(passwordFromSQLDecrypted)) return utilisateur;
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
