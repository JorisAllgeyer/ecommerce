package fr.doranco.ecommerce.cryptage;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public abstract class GenerateKey {
	
	public static final SecretKey getKey(String algorithm, int keysize) throws NoSuchAlgorithmException {
		
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		keyGen.init(keysize);
		return keyGen.generateKey();
	}
}
