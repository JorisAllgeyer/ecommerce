package fr.doranco.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "utilisateur", catalog = "ecommerce_db")
@NamedQueries({
	@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
	@NamedQuery(name = "Utilisateur.findAllEmployes", query = "SELECT u FROM Utilisateur u WHERE NOT  u.role = :role"),
	@NamedQuery(name = "Utilisateur.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email")
})
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name ="nom", length = 25, nullable = false)
	private String nom;
	
	@NotEmpty
	@Column(name ="prenom", length = 25, nullable = false)
	private String prenom;
	
	@NotEmpty
	@Column(name ="genre", length = 5, nullable = false)
	private String genre;
	
	@NotNull
	@Column(name ="date_naissance", nullable = false)
	private Date dateNaissance;
	
	@NotEmpty
	@Column(name ="role", length = 13, nullable = false)
	private String role;
	
	@NotEmpty
	@Column(name ="telephone", length = 13, nullable = false)
	private String telephone;
	
	@NotEmpty
	@Column(name ="email", length = 45, nullable = false, unique = true)
	private String email;
	
	@Column(name ="password", nullable = true)
	private byte[] password;
	
	@Column(name ="cle_cryptage", nullable = true)
	private byte[] cleCryptage;
	
	@NotNull
	@Column(name ="is_actif", columnDefinition = "TINYINT", length = 1, nullable = false)
	private boolean isActif;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private List<Adresse> adresses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private List<CartePaiement> cartesPaiement;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private List<Commande> commandes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private List<ArticlePanier> panier;

	public Utilisateur() {
		this.adresses = new ArrayList<Adresse>();
		this.cartesPaiement = new ArrayList<CartePaiement>();
		this.commandes = new ArrayList<Commande>();
		this.panier = new ArrayList<ArticlePanier>();
	}
	
	public Utilisateur(String nom, String prenom, String genre, @NotNull Date dateNaissance, String role,
			String telephone, String email, byte[] password, byte[] cleCryptage, @NotNull boolean isActif) {
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.dateNaissance = dateNaissance;
		this.role = role;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.cleCryptage = cleCryptage;
		this.isActif = isActif;
		this.adresses = new ArrayList<Adresse>();
		this.cartesPaiement = new ArrayList<CartePaiement>();
		this.commandes = new ArrayList<Commande>();
		this.panier = new ArrayList<ArticlePanier>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public byte[] getCleCryptage() {
		return cleCryptage;
	}

	public void setCleCryptage(byte[] cleCryptage) {
		this.cleCryptage = cleCryptage;
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public List<CartePaiement> getCartesPaiement() {
		return cartesPaiement;
	}

	public void setCartesPaiement(List<CartePaiement> cartesPaiement) {
		this.cartesPaiement = cartesPaiement;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<ArticlePanier> getPanier() {
		return panier;
	}

	public void setPanier(List<ArticlePanier> panier) {
		this.panier = panier;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", dateNaissance="
				+ dateNaissance + ", role=" + role + ", telephone=" + telephone + ", email=" + email + ", password="
				+ Arrays.toString(password) + ", cleCryptage=" + Arrays.toString(cleCryptage) + ", isActif=" + isActif
				+ ", adresses=" + this.getAdresses() + ", cartesPaiement=" + this.getCartesPaiement() + ", commandes=" + this.getCommandes()
				+ ", panier=" + this.getPanier() + "]";
	}
	
}
