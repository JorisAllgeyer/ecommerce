package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.Commande;

public interface IArticle {
// READ	
	List<Article> getArticles() throws Exception;
	Article getArticle(Integer id) throws Exception;

// WRITE	
	Article addArticle(Article article) throws Exception;
	void updateArticle(Article article) throws Exception;
	void removeArticle(Article article) throws Exception;
	
	public void updateStock(Commande commande) throws Exception;
}
