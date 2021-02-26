package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.entity.ArticlePanier;
import fr.doranco.ecommerce.entity.Utilisateur;

public interface IArticlePanierDAO<T> extends IGenericDAO<T> {
	List<ArticlePanier> getArticlesPanier() throws Exception;
	List<ArticlePanier> getArticlesPanierByUser(Utilisateur user) throws Exception;
	ArticlePanier getArticlePanierByUserAndArticle(Utilisateur user, Article article) throws Exception;
	void cleanPanier(Utilisateur user) throws Exception;
	
	List<Utilisateur> getUsers() throws Exception;
}
