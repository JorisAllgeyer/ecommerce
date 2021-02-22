package fr.doranco.ecommerce.entity.enums;

public enum Role {
	CLIENT ("Client"),
	ADMIN ("Admin"),
	MAGASINIER ("Magasinier");
	
	private String role;
	
	Role(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
