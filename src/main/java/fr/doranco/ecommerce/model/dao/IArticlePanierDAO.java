package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.ArticlePanier;

public interface IArticlePanierDAO<T> extends IGenericDAO<T> {
	List<ArticlePanier> getArticlesPanier() throws Exception;
}
