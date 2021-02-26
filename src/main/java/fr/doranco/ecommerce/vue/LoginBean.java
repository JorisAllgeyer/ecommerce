package fr.doranco.ecommerce.vue;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
	public void initializeFields() {
		this.email = "";
		this.password = "";
	}
	
	public LoginBean() {
		initializeFields();
	}

	public String seConnecter() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		IUtilisateur userImpl = new UtilisateurImpl();
		Utilisateur user = userImpl.connect(email, password);
		
		if (user != null) {
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Utilisateur connecté avec succes"));
		} else {
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Login et/ou Mot de passe incorrect(s)"));
			return "";
		}
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("currentUser", user);
		
		if (user.getRole().equals(Role.ADMIN.getRole())) return "gestion-admin";
		if (user.getRole().equals(Role.MAGASINIER.getRole())) return "gestion-articles";
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

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

}
