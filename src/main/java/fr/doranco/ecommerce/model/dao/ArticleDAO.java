package fr.doranco.ecommerce.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.doranco.ecommerce.entity.Article;
import fr.doranco.ecommerce.model.connector.HibernateConnector;

public class ArticleDAO 
	extends AbstractGenericDAO<Article> 
	implements IArticleDAO<Article> {
	

	@Override
	public List<Article> getArticles() {
		Session session = HibernateConnector.getInstance().getSession();

		try {
			Query query = session.createNamedQuery("Article.findAll");
			return (List<Article>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}

}
