package fr.doranco.ecommerce.vue;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.exception.ConstraintViolationException;

import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.model.dao.DuplicateEntryExcpetion;
import fr.doranco.ecommerce.utils.Dates;
import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;

@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class UtilisateurBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(name = "userId", value = "")
	private String userId;
	
	@ManagedProperty(name = "nom", value = "")
	private String nom;
	
	@ManagedProperty(name = "prenom", value = "")
	private String prenom;
	
	@ManagedProperty(name = "genre", value = "")
	private String genre;
	
	@ManagedProperty(name = "dateNaissance", value = "")
	private String dateNaissance;
	
	@ManagedProperty(name = "role", value = "")
	private String role;
	
	@ManagedProperty(name = "telephone", value = "")
	private String telephone;
	
	@ManagedProperty(name = "email", value = "")
	private String email;
	
	@ManagedProperty(name = "password", value = "")
	private String password;
	
	@ManagedProperty(name = "passwordConfirm", value = "")
	private String passwordConfirm;
	
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
		this.userId  = "";
		this.nom = "";
		this.prenom = "";
		this.genre = "";
		this.email = "";
		this.telephone = "";
		this.dateNaissance = "";
		this.role = "";
		this.password = "";
		this.passwordConfirm = "";
	}
	
	public UtilisateurBean() {
		initializeMessages();
		initializeFields();
	}
	
// Methods	
	public String addUser() {
		initializeMessages();
		
		if (!this.password.equals(this.passwordConfirm)) {
			messageError = "Erreur ! les deux mots de passe ne correspondent pas !";
			return "";
		}
		
		Utilisateur user = new Utilisateur();
		user.setGenre(genre);
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setDateNaissance(Dates.stringToDate(dateNaissance));
		user.setRole(Role.CLIENT.getRole());
		user.setActif(true);
		
		IUtilisateur userImpl = new UtilisateurImpl();
		
		try {
			Utilisateur userCreated = userImpl.addUtilisateur(user, password);
			this.userId = userCreated.getId().toString();
			messageSuccess = "Utilisateur crée avec succès !";
			
		} catch (DuplicateEntryExcpetion e) {
			
			messageError = "Cet email existe déjà dans notre base, Veuillez-vous connecter.";
			return "";
		} catch (Exception e) {
			
			messageError = "Problème lors de l'ajout d'un utilisateur";
			return "";
		}
		
		return "login-utilisateur?faces-redirect=true&success=true";
	}

// Fields
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
// Messages
	public String getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(String messageSuccess) {
		UtilisateurBean.messageSuccess = messageSuccess;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		UtilisateurBean.messageError = messageError;
	}

}
