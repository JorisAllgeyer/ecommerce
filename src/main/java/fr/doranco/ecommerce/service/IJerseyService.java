package fr.doranco.ecommerce.service;

import java.util.List;
import java.util.Map;

import fr.doranco.ecommerce.entity.ArticlePanier;

public interface IJerseyService {
// INFOS
	List<String> getUtilisateursAvecPanierNonVide();

	String ping();
}