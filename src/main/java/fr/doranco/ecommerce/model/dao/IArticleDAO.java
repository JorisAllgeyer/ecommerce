package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.Article;

public interface IArticleDAO<T> extends IGenericDAO<T> {
	List<Article> getArticles() throws Exception;
}
