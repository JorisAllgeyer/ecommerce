package fr.doranco.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "article_panier", catalog = "ecommerce_db")
@NamedQueries({
	@NamedQuery(name = "ArticlePanier.findByUser", query = "SELECT ap FROM ArticlePanier ap WHERE ap.utilisateur = :user"),
	@NamedQuery(name = "ArticlePanier.findByUserAndArticle", 
		query = "SELECT ap FROM ArticlePanier ap WHERE ap.utilisateur = :user AND ap.article = :article"),
	@NamedQuery(name = "ArticlePanier.cleanUserPanier", query = "DELETE FROM ArticlePanier ap WHERE ap.utilisateur = :user"),
	@NamedQuery(name = "ArticlePanier.getUsers", query = "SELECT DISTINCT u FROM ArticlePanier ap INNER JOIN ap.utilisateur u")
})
public class ArticlePanier implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "article_id", nullable = false, insertable = true)
	private Article article;
	
	@NotNull
	@Column(name ="quantite", nullable = false)
	private Integer quantite;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id", nullable = false, insertable = true)
	private Utilisateur utilisateur;
	
	public ArticlePanier() {}

	public ArticlePanier(Article article, @NotNull Integer quantite, Utilisateur utilisateur) {
		this.article = article;
		this.quantite = quantite;
		this.utilisateur = utilisateur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "ArticlePanier [id=" + id + ", article=" + article + ", quantite=" + quantite + "]";
	}
	
}
