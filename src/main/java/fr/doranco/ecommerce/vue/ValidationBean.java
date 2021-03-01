package fr.doranco.ecommerce.vue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.doranco.ecommerce.control.AdresseImpl;
import fr.doranco.ecommerce.control.ArticleImpl;
import fr.doranco.ecommerce.control.ArticlePanierImpl;
import fr.doranco.ecommerce.control.CommandeImpl;
import fr.doranco.ecommerce.control.IAdresse;
import fr.doranco.ecommerce.control.IArticle;
import fr.doranco.ecommerce.control.IArticlePanier;
import fr.doranco.ecommerce.control.ICommande;
import fr.doranco.ecommerce.entity.Adresse;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Commande;
import fr.doranco.ecommerce.entity.LigneCommande;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.utils.Dates;

@ManagedBean(name = "validationBean")
@RequestScoped
public class ValidationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(name = "adresseId", value = "")
	private String adresseId;
		
	@ManagedProperty(name = "messageColor", value = "")
	private String messageColor;
	
	private static Float totalPanier;
	private static Float totalRemise;
	
	static { 
		totalPanier = 0f;
		totalRemise = 0f;
	}
	
	public List<Adresse> getUserAdresses(Utilisateur user) {
		return user.getAdresses();
	}
	
	public void setDeliveryAdresse() {
		System.out.println("adresse delivery selected: " + adresseId);
	}
	
	public String validateOrder(Utilisateur user) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		IArticlePanier articlePanierImpl = new ArticlePanierImpl();
		IAdresse adresseImpl = new AdresseImpl();
		IArticle articleImpl = new ArticleImpl();
		
		try {
			List<ArticlePanier> panier = articlePanierImpl.getArticlesPanierByUser(user);
			user.setPanier(panier);
			
			// Init lignesCommande
			List<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
			
			// Adresse
			Adresse adresse = adresseImpl.getAdresse(Integer.parseInt(adresseId));
			
			// Init totals
			totalPanier = 0f;
			totalRemise = 0f;
			
			panier.forEach(ap -> {
				// Total Price
				Float price = ap.getArticle().getPrix();
				Float priceWithRemise = price - (price * ap.getArticle().getRemise() / 100);
				Float finalPrice = priceWithRemise * ap.getQuantite();
				totalPanier += finalPrice;
				
				// Total Remise
				Float remiseUnitaire = price * ap.getArticle().getRemise() / 100;
				Float remiseWithQty = remiseUnitaire * ap.getQuantite();
				totalRemise += remiseWithQty;
				
				// Ligne Commande
				LigneCommande ligneCommande = new LigneCommande();
				ligneCommande.setQuantite(ap.getQuantite());
				ligneCommande.setPrixUnitaire(price);
				ligneCommande.setRemiseUnitaire(ap.getArticle().getRemise());
				ligneCommande.setTva(20);
				ligneCommande.setArticle(ap.getArticle());
				lignesCommande.add(ligneCommande);
			});
			
			// Commande
			Commande commande = new Commande();
			commande.setDateCreation(Dates.convertDateUtilToDateSql(new Date(System.currentTimeMillis())));
			commande.setDateLivraison(Dates.convertDateUtilToDateSql(new Date(System.currentTimeMillis())));
			commande.setLignesCommande(lignesCommande);
			commande.setTotalGeneral(totalPanier);
			commande.setTotalRemise(totalRemise);
			commande.setUtilisateur(user);
			commande.setAdresse(adresse);
			
			// Set Commmande
			lignesCommande.forEach(ligne -> ligne.setCommande(commande));
			
			ICommande commandeImpl = new CommandeImpl();
			Commande commandeCreated = commandeImpl.addCommande(commande);
			user.getCommandes().add(commandeCreated);
			
			// Scope commande
			session.setAttribute("currentOrder", commandeCreated);
			
			// Clean Panier
			articlePanierImpl.cleanPanier(user);
			user.setPanier(new ArrayList<ArticlePanier>());
			
			// Update Stock
			articleImpl.updateStock(commandeCreated);
			
			this.messageColor = "green";
			context.addMessage(null, new FacesMessage("Commande passée avec succès."));
			return "confirmation";
			
		} catch (Exception e) {
			this.messageColor = "red";
			context.addMessage(null, new FacesMessage("Erreur lors de la commande."));
			e.printStackTrace();
			return "";
		}
		
	}
	
	public String getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(String adresseId) {
		this.adresseId = adresseId;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	
}
