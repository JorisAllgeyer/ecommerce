package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.doranco.ecommerce.entity.Adresse;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.model.dao.DuplicateEntryExcpetion;
import fr.doranco.ecommerce.utils.Dates;
import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;

@ManagedBean(name = "utilisateurBean")
@RequestScoped
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
	
	@ManagedProperty(name = "numero", value = "")
	private String numero;
	
	@ManagedProperty(name = "rue", value = "")
	private String rue;

	@ManagedProperty(name = "ville", value = "")
	private String ville;
	
	@ManagedProperty(name = "codePostal", value = "")
	private String codePostal;
	
	@ManagedProperty(name = "adresses", value = "")
	private static List<Adresse> adresses; 
	
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
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
	
	static {
		adresses = new ArrayList<Adresse>();		
	}
	
	public UtilisateurBean() {
		initializeFields();
	}
	
// Methods	
	public String addUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (!this.password.equals(this.passwordConfirm)) {
			this.messageColor = "red";
			context.addMessage(null, 
				new FacesMessage("Erreur ! les deux mots de passe ne correspondent pas !"));
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
		
		// Adresses
		adresses.forEach(adresse -> adresse.setUtilisateur(user));
		user.setAdresses(adresses);
		
		IUtilisateur userImpl = new UtilisateurImpl();
		
		try {
			Utilisateur userCreated = userImpl.addUtilisateur(user, password);
			this.userId = userCreated.getId().toString();
			
			this.messageColor = "green";
			context.addMessage(null, 
				new FacesMessage("Utilisateur crée avec succès !"));
			
		} catch (DuplicateEntryExcpetion e) {
			this.messageColor = "red";
			context.addMessage(null, 
				new FacesMessage("Cet email existe déjà dans notre base, Veuillez-vous connecter."));
			return "";
		} catch (Exception e) {
			context.addMessage(null, 
					new FacesMessage("Problème lors de l'ajout d'un utilisateur"));
			return "";
		}
		
		return "login-utilisateur?faces-redirect=true&success=true";
	}
	
	public String addAdresse() {
		Adresse adresse = new Adresse(Integer.parseInt(numero), rue, ville, codePostal);
		adresses.add(adresse);
		return "";
	}
	
	public String removeAdresse(Adresse adresse) {
		System.out.println(adresse);
		adresses.remove(adresse);
		return "";
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
	
// Adresse
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

// Messages
	
	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		UtilisateurBean.adresses = adresses;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

}
