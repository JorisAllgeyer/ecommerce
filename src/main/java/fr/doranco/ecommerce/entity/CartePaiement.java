package fr.doranco.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "carte_paiement", catalog = "ecommerce_db")
// TODO: 
@Embeddable
public class CartePaiement implements Serializable  {
	
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
	
	@NotNull
	@Column(name ="date_fin_validite", nullable = false)
	private Date dateFinValidite;
	
	@NotNull
	@Column(name ="cryptogramme", nullable = false)
	private Integer cryptogramme;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id", nullable = false, insertable = true)
	private Utilisateur utilisateur;

	public CartePaiement() {}
	
	public CartePaiement(String nom, String prenom, @NotNull Date dateFinValidite, @NotNull Integer cryptogramme,
			Utilisateur utilisateur) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateFinValidite = dateFinValidite;
		this.cryptogramme = cryptogramme;
		this.utilisateur = utilisateur;
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

	public Date getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public Integer getCryptogramme() {
		return cryptogramme;
	}

	public void setCryptogramme(Integer cryptogramme) {
		this.cryptogramme = cryptogramme;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "CartePaiement [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateFinValidite="
				+ dateFinValidite + ", cryptogramme=" + cryptogramme + ", utilisateur_id=" + utilisateur.getId() + "]";
	}
	
}
