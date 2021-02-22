package fr.doranco.ecommerce.entity.enums;

public enum Genre {

	MONSIEUR ("M."),
	MADAME ("Mme"),
	MADEMOISELLE ("Mlle");
	
	private String genre;
	
	//Constructeur
	Genre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return this.genre;
	}
}
