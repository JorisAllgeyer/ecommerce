package fr.doranco.ecommerce.service;

import java.util.List;
import java.util.Map;

import fr.doranco.ecommerce.entity.Article;

public interface IJerseyService {
	String ping();
	Map<String, Map<Article, Integer>> getUtilisateursAvecPanierNonVide();
	List<String> getEmailsUtilisateursAvecPanierNonVide();
}