package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.model.dao.ArticleDAO;
import fr.doranco.ecommerce.model.dao.IArticleDAO;

public class ArticleImpl implements IArticle {

	@Override
	public List<Article> getArticles() throws Exception {
		IArticleDAO<Article> articleDAO = new ArticleDAO();
		return articleDAO.getArticles();
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

}
