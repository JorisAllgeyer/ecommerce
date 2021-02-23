package fr.doranco.ecommerce.cryptage;

public enum AlgoCryptage {
	DES ("DES"),
	DSA ("DSA"),
	PBE ("PBE"),
	BLOWFISH ("BLOWFISH");
	
	private String algorithm;
	
	AlgoCryptage(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getAlgo() {
		return this.algorithm;
	}

}
