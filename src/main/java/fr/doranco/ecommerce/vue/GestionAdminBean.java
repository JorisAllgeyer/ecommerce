package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.entity.enums.Role;
import fr.doranco.ecommerce.utils.Dates;

@ManagedBean(name = "gestionAdminBean")
@SessionScoped
public class GestionAdminBean implements Serializable  {

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
	
	@ManagedProperty(name = "isActif", value = "")
	private Boolean isActif;
	
	@ManagedProperty(name = "messageSuccess", value = "")
	private static String messageSuccess;
	
	@ManagedProperty(name = "messageError", value = "")
	private static String messageError;
	
	@ManagedProperty(name = "updateAction", value = "false")
	private String updateAction;
	
	private byte[] motDePasseCrypte;
	private byte[] cleCryptage;

	static {
		messageSuccess = "";
		messageError = "";
	}
	
	private void initializeMessages() {
		messageSuccess = "";
		messageError = "";
	}
	
	private void initializeFields() {
	    this.nom = "";
	    this.prenom = "";
	    this.genre = "";
	    this.email = "";
	    this.motDePasseCrypte = null;
	    this.cleCryptage = null;
	    this.telephone = "";
	    this.dateNaissance = "";
	    this.role = "";
	}
	
	public GestionAdminBean() {
		initializeMessages();
		initializeFields();
	}
	
	public List<Utilisateur> getListeEmployes() {
		initializeMessages();
		
		updateAction = "false";
		IUtilisateur userImpl = new UtilisateurImpl();
		return userImpl.getEmployes();
	}
	
	public String displayUtilisateur(Utilisateur user) {
		initializeMessages();
		updateAction = "true";
		
		this.userId = user.getId().toString();
	    this.nom = user.getNom();
	    this.prenom = user.getPrenom();
	    this.genre = user.getGenre();
	    this.email = user.getEmail();
	    this.motDePasseCrypte = user.getPassword();
	    this.cleCryptage = user.getCleCryptage();
	    this.telephone = user.getTelephone();
	    this.dateNaissance = Dates.dateToString(user.getDateNaissance());
	    this.role = user.getRole();
	    this.isActif = user.isActif();
		
		return "";
	}
	
	public String updateUtilisateur() {
		initializeMessages();
		
		Utilisateur user = new Utilisateur();
		user.setId(Integer.parseInt(userId));
		user.setGenre(genre);
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setPassword(motDePasseCrypte);
		user.setCleCryptage(cleCryptage);
		user.setTelephone(telephone);
		user.setDateNaissance(Dates.stringToDate(dateNaissance));
		user.setRole(role);
		user.setActif(isActif);
		
		IUtilisateur userImpl = new UtilisateurImpl();
		
		try {
			userImpl.updateUtilisateur(user);
			messageSuccess = "Utilisateur modifié avec succès !";
		} catch (Exception e) {
			messageError = "Problème lors de la modification";
		}
		
		return "";
	}

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

	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}

	public byte[] getMotDePasseCrypte() {
		return motDePasseCrypte;
	}

	public void setMotDePasseCrypte(byte[] motDePasseCrypte) {
		this.motDePasseCrypte = motDePasseCrypte;
	}

	public byte[] getCleCryptage() {
		return cleCryptage;
	}

	public void setCleCryptage(byte[] cleCryptage) {
		this.cleCryptage = cleCryptage;
	}

	public String getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(String messageSuccess) {
		GestionAdminBean.messageSuccess = messageSuccess;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		GestionAdminBean.messageError = messageError;
	}
	
	public String getUpdateAction() {
		return updateAction;
	}

	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}
	
}
