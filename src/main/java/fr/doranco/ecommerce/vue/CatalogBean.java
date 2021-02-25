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
	
	// Map Article / Qty
	private static Map<Integer, Integer> articleQty;
	private static Float totalPanier;
	static { 
		articleQty = new HashMap<Integer, Integer>();
		totalPanier = 0f;
	}
	
	public CatalogBean() {
		
	}
	
	public List<ArticlePanier> getArticlesPanier(String userEmail) {
		// User
		IUtilisateur userImpl = new UtilisateurImpl();
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		
		try {
			Utilisateur user = userImpl.getUtilisateurByEmail(userEmail);
			return articlePanierImpl.getArticlesPanierByUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		articleQty.put(Integer.parseInt(id), Integer.parseInt(qty));	
	}
	
	public String incrementQty(ArticlePanier ap) {
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		try {
			ap.setQuantite(ap.getQuantite() + 1);
			articlePanierImpl.updateArticlePanier(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String decrementQty(ArticlePanier ap) {
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		try {
			ap.setQuantite(ap.getQuantite() - 1);
			articlePanierImpl.updateArticlePanier(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String removeArticlePanier(ArticlePanier ap) {
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		try {
			articlePanierImpl.removeArticlePanier(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getTotal(String userEmail) {
		// User
		IUtilisateur userImpl = new UtilisateurImpl();
		// ArticlePanier
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		
		try {
			Utilisateur user = userImpl.getUtilisateurByEmail(userEmail);
			List<ArticlePanier> articlesPanier = articlePanierImpl.getArticlesPanierByUser(user);
			totalPanier = 0f;
			articlesPanier.forEach(ap -> {
				Float price = ap.getArticle().getPrix();
				Float priceWithRemise = price - (price * ap.getArticle().getRemise() / 100);
				Float finalPrice = priceWithRemise * ap.getQuantite();
				totalPanier += finalPrice;
			});

			return totalPanier.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "";
	}
	
	public String addToCart(Article article, String userEmail) {
		FacesContext context = FacesContext.getCurrentInstance();
		// User
		IUtilisateur userImpl = new UtilisateurImpl();
		
		// ArticlePanier
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();

		Integer currentArticleQty = articleQty.get(article.getId()) != null 
			? articleQty.get(article.getId())
			: 1;
		
		try {
			Utilisateur user = userImpl.getUtilisateurByEmail(userEmail);
			ArticlePanier articlePanierFromSQL = articlePanierImpl.getArticlePanierByUserAndArticle(user, article);
			
			// ArticlePanier already exists, update it. 
			if (articlePanierFromSQL != null) {
				
				articlePanierFromSQL.setQuantite(articlePanierFromSQL.getQuantite() + currentArticleQty);
				articlePanierImpl.updateArticlePanier(articlePanierFromSQL);
				
			} else {
				// ArticlePanier does not exists, create it.
				ArticlePanier articlePanier = new ArticlePanier(article, currentArticleQty, user);
				articlePanierImpl.addArticlePanier(articlePanier);
			}
			
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Article ajouté au panier avec succès !"));
			
		} catch (Exception e) {
			e.printStackTrace();
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Problème lors de l'ajout au panier"));
		}
		
		return "";
	}
	
	public String validatePanier() {
		
		return "validation";
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

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	
}
