package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.ArticlePanier;

public interface IArticlePanier {
	
// READ	
	List<ArticlePanier> getArticlesPanier() throws Exception;

// WRITE	
	ArticlePanier addArticlePanier(ArticlePanier article) throws Exception;
	void updateArticlePanier(ArticlePanier article) throws Exception;
	void removeArticlePanier(ArticlePanier article) throws Exception;
}
