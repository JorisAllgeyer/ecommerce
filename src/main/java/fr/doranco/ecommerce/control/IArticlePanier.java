package fr.doranco.ecommerce.control;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;

public interface IArticlePanier {
	
// READ
	ArticlePanier getArticlePanier(ArticlePanier articlePanier) throws Exception;
	List<ArticlePanier> getArticlesPanier() throws Exception;
	List<ArticlePanier> getArticlesPanierByUser(Utilisateur user) throws Exception;
	ArticlePanier getArticlePanierByUserAndArticle(Utilisateur user, Article article) throws Exception;

// WRITE	
	ArticlePanier addArticlePanier(ArticlePanier article) throws Exception;
	void updateArticlePanier(ArticlePanier article) throws Exception;
	void removeArticlePanier(ArticlePanier article) throws Exception;
}
