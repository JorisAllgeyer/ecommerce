package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;

public interface IArticle {
// READ	
	List<Article> getArticles() throws Exception;

// WRITE	
	Article addArticle(Article article) throws Exception;
	void updateArticle(Article article) throws Exception;
	void removeArticle(Article article) throws Exception;
}
