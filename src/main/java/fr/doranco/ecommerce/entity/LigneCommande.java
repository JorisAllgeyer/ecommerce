package fr.doranco.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.Commande;

@Entity
@Table(name = "ligne_commande", catalog = "ecommerce_db")
public class LigneCommande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name ="quantite", nullable = false)
	private Integer quantite;

	@NotNull
	@Column(name ="prix_unitaire", nullable = false)
	private Float prixUnitaire;
	
	@NotNull
	@Column(name ="remise_unitaire", nullable = false)
	private Integer remiseUnitaire;
	
	@NotNull
	@Column(name ="tva", nullable = false)
	private Integer tva;
	
	@ManyToOne
	@JoinColumn(name = "commande_id", nullable = false, insertable = true)
	private Commande commande;
	
	@OneToOne
	@JoinColumn(name = "article_id", nullable = false, insertable = true)
	private Article article;
	
	public LigneCommande() {}
	
	public LigneCommande(@NotNull Integer quantite, @NotNull Float prixUnitaire, @NotNull Integer remiseUnitaire,
			@NotNull Integer tva, Commande commande, Article article) {
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.remiseUnitaire = remiseUnitaire;
		this.tva = tva;
		this.commande = commande;
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Integer getRemiseUnitaire() {
		return remiseUnitaire;
	}

	public void setRemiseUnitaire(Integer remiseUnitaire) {
		this.remiseUnitaire = remiseUnitaire;
	}

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire
				+ ", remiseUnitaire=" + remiseUnitaire + ", tva=" + tva + ", commande_id=" + commande.getId() + ", article_id="
				+ article.getId() + "]";
	}
	
}
