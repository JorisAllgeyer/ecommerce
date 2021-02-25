package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;
import fr.doranco.ecommerce.entity.Adresse;
import fr.doranco.ecommerce.entity.Utilisateur;


@ManagedBean(name = "validationBean")
@RequestScoped
public class ValidationBean implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
	public List<Adresse> getUserAdresses(String userEmail) {
		
		IUtilisateur userImpl = new UtilisateurImpl();
		
		try {
			Utilisateur user = userImpl.getUtilisateurByEmail(userEmail);
			return user.getAdresses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void setAdresseUser() {
		System.out.println("coucou");
		// System.out.println("adresse selected -> " + adresse.getVille());
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	
}
