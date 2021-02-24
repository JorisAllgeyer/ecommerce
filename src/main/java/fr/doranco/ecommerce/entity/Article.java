package fr.doranco.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "article", catalog = "ecommerce_db")
@NamedQueries({
	@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
})
public class Article implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
	@Column(name ="nom", length = 100, nullable = false)
	private String nom;

	@NotEmpty
	@Column(name ="description", length = 500, nullable = false)
	private String description;
	
	@NotNull
	@Column(name ="prix", nullable = false)
	private Float prix;
	
	@NotNull
	@Column(name ="remise", nullable = false)
	private Integer remise;
	
	@NotNull
	@Column(name ="stock", nullable = false)
	private Integer stock;
	
	public Article() {}

	public Article(String nom, String description, @NotNull Float prix, @NotNull Integer remise,
			@NotNull Integer stock) {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.remise = remise;
		this.stock = stock;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public Integer getRemise() {
		return remise;
	}

	public void setRemise(Integer remise) {
		this.remise = remise;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", remise="
				+ remise + ", stock=" + stock + "]";
	}
	
}
