package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.doranco.ecommerce.control.ArticleImpl;
import fr.doranco.ecommerce.control.IArticle;
import fr.doranco.ecommerce.entity.Article;

@ManagedBean(name = "gestionArticlesBean")
@RequestScoped
public class GestionArticlesBean implements Serializable {
	
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

	@ManagedProperty(name = "stock", value = "")
	private String stock;
	
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
	@ManagedProperty(name = "updateAction", value = "false")
	private String updateAction;
	
	private void initializeFields() {
		this.nom = "";
		this.description = "";
		this.prix = "";
		this.remise = "";
		this.stock = "";
	}

	public GestionArticlesBean() {
		initializeFields();
	}
	
	public List<Article> getListArticles() {
		updateAction = "false";

		IArticle articleImpl = new ArticleImpl();
		
		try {
			return articleImpl.getArticles();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String displayArticle(Article article) {
		updateAction = "true";
		
		this.articleId = article.getId().toString();
		this.nom = article.getNom();
		this.description = article.getDescription();
		this.prix = article.getPrix().toString();
		this.remise = article.getRemise().toString();
		this.stock = article.getStock().toString();
		
		return "";
	}
	
	
	public String addArticle() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Article article = new Article();
		article.setNom(nom);
		article.setDescription(description);
		article.setPrix(Float.parseFloat(prix));
		article.setRemise(Integer.parseInt(remise));
		article.setStock(Integer.parseInt(stock));
		
		IArticle articleImpl = new ArticleImpl();
		
		try {
			articleImpl.addArticle(article);
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Article ajouté avec succès !"));
		} catch (Exception e) {
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Problème lors de l'ajout"));
		}
		
		return "";
	}
	
	public String updateArticle() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Article article = new Article();
		article.setId(Integer.parseInt(articleId));
		article.setNom(nom);
		article.setDescription(description);
		article.setPrix(Float.parseFloat(prix));
		article.setRemise(Integer.parseInt(remise));
		article.setStock(Integer.parseInt(stock));
		
		IArticle articleImpl = new ArticleImpl();
		
		try {
			articleImpl.updateArticle(article);
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Article modifié avec succès !"));
		} catch (Exception e) {
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Problème lors de la modification"));
		}
		
		return "";
	}
	
	public String removeArticle(Article article) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		IArticle articleImpl = new ArticleImpl();
		
		try {
			articleImpl.removeArticle(article);
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Article supprimé avec succès !"));
		} catch (Exception e) {
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Problème lors de la suppression"));
		}
		
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

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getUpdateAction() {
		return updateAction;
	}

	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	
}
