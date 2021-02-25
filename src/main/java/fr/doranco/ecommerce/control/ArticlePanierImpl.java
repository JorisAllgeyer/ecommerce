package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;
import fr.doranco.ecommerce.model.dao.ArticlePanierDAO;
import fr.doranco.ecommerce.model.dao.IArticlePanierDAO;

public class ArticlePanierImpl implements IArticlePanier {
	
	@Override
	public ArticlePanier getArticlePanier(ArticlePanier articlePanier) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.get(ArticlePanier.class, articlePanier.getId());
	}

	@Override
	public List<ArticlePanier> getArticlesPanier() throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.getArticlesPanier();
	}
	
	@Override
	public ArticlePanier getArticlePanierByUserAndArticle(Utilisateur user, Article article) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.getArticlePanierByUserAndArticle(user, article);
	}

	@Override
	public List<ArticlePanier> getArticlesPanierByUser(Utilisateur user) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.getArticlesPanierByUser(user);
	}
	
	@Override
	public ArticlePanier addArticlePanier(ArticlePanier articlePanier) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.add(articlePanier);
	}

	@Override
	public void updateArticlePanier(ArticlePanier articlePanier) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		articlePanierDAO.update(articlePanier);
	}

	@Override
	public void removeArticlePanier(ArticlePanier articlePanier) throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		articlePanierDAO.remove(articlePanier);
	}



}
