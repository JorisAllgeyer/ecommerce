package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.model.dao.ArticlePanierDAO;
import fr.doranco.ecommerce.model.dao.IArticlePanierDAO;

public class ArticlePanierImpl implements IArticlePanier {

	@Override
	public List<ArticlePanier> getArticlesPanier() throws Exception {
		IArticlePanierDAO<ArticlePanier> articlePanierDAO = new ArticlePanierDAO();
		return articlePanierDAO.getArticlesPanier();
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
