package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.doranco.ecommerce.control.ArticleImpl;
import fr.doranco.ecommerce.control.ArticlePanierImpl;
import fr.doranco.ecommerce.control.IArticle;
import fr.doranco.ecommerce.control.IArticlePanier;
import fr.doranco.ecommerce.control.IUtilisateur;
import fr.doranco.ecommerce.control.UtilisateurImpl;
import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;

@ManagedBean(name = "catalogBean")
@RequestScoped
public class CatalogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(name = "articleId", value = "")
	private String articleId;
	
	@ManagedProperty(name = "nom", value = "")
	private String nom;
	
	@ManagedProperty(name = "description", value = "")
	private String description;

	@ManagedProperty(name = "prix", value = "")
	private String prix;
	
	@ManagedProperty(name = "remise", value = "")
	private String remise;

	@ManagedProperty(name = "qty", value = "")
	private String qty;
		
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
	private Map<Integer, Integer> articleQty;
	
	public CatalogBean() {
		this.articleQty = new HashMap<Integer, Integer>();
	}
	
	public List<Article> getListArticles() {

		IArticle articleImpl = new ArticleImpl();
		
		try {
			return articleImpl.getArticles();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void changeQty(String id) {
		System.out.println("changeQty -> " + id + ":" + qty);
		articleQty.put(Integer.parseInt(id), Integer.parseInt(qty));
		
		System.out.println("articleQty -> " + articleQty);
	}
	
	public String addToCart(Article article, String userEmail) {
		
		// User
		IUtilisateur userImpl = new UtilisateurImpl();
		Utilisateur user = userImpl.getUtilisateurByEmail(userEmail);
		
		// ArticlePanier
		Integer currentArticleQty = articleQty.get(article.getId()) != null 
			? articleQty.get(article.getId())
			: 1;
		
		ArticlePanier articlePanier = new ArticlePanier(article, currentArticleQty, user);
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			ArticlePanier articlePanierAdded = articlePanierImpl.addArticlePanier(articlePanier);
			System.out.println("articlePanierAdded -> " + articlePanierAdded);
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Article ajouté au panier avec succès !"));
		} catch (Exception e) {
			e.printStackTrace();
			
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Problème lors de l'ajout au panier"));
		}
		
		
		// TODO Map<Article, Integer>
		return "";
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getRemise() {
		return remise;
	}

	public void setRemise(String remise) {
		this.remise = remise;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public Map<Integer, Integer> getArticleQty() {
		return articleQty;
	}

	public void setArticleQty(Map<Integer, Integer> articleQty) {
		this.articleQty = articleQty;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	
}
