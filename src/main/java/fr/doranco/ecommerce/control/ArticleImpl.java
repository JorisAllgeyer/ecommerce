package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.Commande;
import fr.doranco.ecommerce.entity.LigneCommande;
import fr.doranco.ecommerce.model.dao.ArticleDAO;
import fr.doranco.ecommerce.model.dao.IArticleDAO;

public class ArticleImpl implements IArticle {

	@Override
	public List<Article> getArticles() throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		return articleDAO.getArticles();
	}

	@Override
	public Article getArticle(Integer id) throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		return articleDAO.get(Article.class, id);
	}
	
	@Override
	public Article addArticle(Article article) throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		return articleDAO.add(article);
	}

	@Override
	public void updateArticle(Article article) throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		articleDAO.update(article);
	}
	
	@Override
	public void removeArticle(Article article) throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		articleDAO.remove(article);
	}
	
	public void updateStock(Commande commande) throws Exception {
		List<LigneCommande> lignesCommande = commande.getLignesCommande();
		IArticle articleImpl = new ArticleImpl();
		
		try {
			
			for (LigneCommande ligneCommande : lignesCommande) {
				Article article = ligneCommande.getArticle();
				
				Integer currentArticleStock = article.getStock();
				Integer qtyOrdered = ligneCommande.getQuantite();
				article.setStock(currentArticleStock - qtyOrdered);
				
				articleImpl.updateArticle(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}

}
