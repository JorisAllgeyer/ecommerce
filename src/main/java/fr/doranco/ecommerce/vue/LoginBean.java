package fr.doranco.ecommerce.vue;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Role;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(name = "email", value = "")
	private String email;
	
	@ManagedProperty(name = "password", value = "")
	private String password;
	
	@ManagedProperty(name = "messageSuccess", value = "")
	private static String messageSuccess;
	
	@ManagedProperty(name = "messageError", value = "")
	private static String messageError;
	
	static {
		messageSuccess = "";
		messageError = "";
	}
	
	private void initializeMessages() {
		messageSuccess = "";
		messageError = "";
	}
	
	public void initializeFields() {
		this.email = "";
		this.password = "";
	}
	
	public LoginBean() {
		initializeMessages();
		initializeFields();
	}

	public String seConnecter() {
		initializeMessages();
		
		IUtilisateur userImpl = new UtilisateurImpl();
		Utilisateur user = userImpl.connect(email, password);
		
		if (user != null) {
			messageSuccess = "Utilisateur connecté avec succes";
		} else {
			messageError = "Login et/ou Mot de passe incorrect(s)";
			return "";
		}
		
		if (user.getRole().equals(Role.ADMIN.getRole())) return "gestion-admin";
		if (user.getRole().equals(Role.MAGASINIER.getRole())) return "gestion-article";
		if (user.getRole().equals(Role.CLIENT.getRole())) return "catalog";
		return "";
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(String messageSuccess) {
		LoginBean.messageSuccess = messageSuccess;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		LoginBean.messageError = messageError;
	}
}
