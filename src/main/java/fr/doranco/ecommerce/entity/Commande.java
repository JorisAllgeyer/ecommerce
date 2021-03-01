package fr.doranco.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "commande", catalog = "ecommerce_db")
public class Commande implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name ="numero", length = 25, nullable = false)
	private String numero;
	
	@NotNull
	@Column(name ="date_creation", nullable = false)
	private Date dateCreation;

	@NotNull
	@Column(name ="date_livraison", nullable = true)
	private Date dateLivraison;
	
	@NotNull
	@Column(name ="total_remise", nullable = true)
	private float totalRemise;
	
	@NotNull
	@Column(name ="total_general", nullable = true)
	private float totalGeneral;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id", nullable = false, insertable = true)
	private Utilisateur utilisateur;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", fetch=FetchType.LAZY)
	private List<LigneCommande> lignesCommande;
	
	@ManyToOne
	@JoinColumn(name = "adresse_id", nullable = false, insertable = true)
	private Adresse adresse;
	
	public Commande() {
		this.lignesCommande = new ArrayList<LigneCommande>();
	}
	
	public Commande(String numero, @NotNull Date dateCreation, @NotNull Date dateLivraison, @NotNull float totalRemise,
			@NotNull float totalGeneral, Utilisateur utilisateur, List<LigneCommande> lignesCommande) {
		this.numero = numero;
		this.dateCreation = dateCreation;
		this.dateLivraison = dateLivraison;
		this.totalRemise = totalRemise;
		this.totalGeneral = totalGeneral;
		this.utilisateur = utilisateur;
		this.lignesCommande = new ArrayList<LigneCommande>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public float getTotalRemise() {
		return totalRemise;
	}

	public void setTotalRemise(float totalRemise) {
		this.totalRemise = totalRemise;
	}

	public float getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(float totalGeneral) {
		this.totalGeneral = totalGeneral;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", numero=" + numero + ", dateCreation=" + dateCreation + ", dateLivraison="
				+ dateLivraison + ", totalRemise=" + totalRemise + ", totalGeneral=" + totalGeneral + ", utilisateur_id="
				+ utilisateur.getId() + "]";
	}
	
}
