package fr.doranco.ecommerce.cryptage;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import fr.doranco.ecommerce.cryptage.AlgoCryptage;

public abstract class CryptageDES {
	
	public static final byte[] encrypt(String messageToEncrypt, SecretKey key) 
		throws NoSuchAlgorithmException, 
		NoSuchPaddingException, 
		InvalidKeyException, 
		IllegalBlockSizeException, 
		BadPaddingException {
		
		Cipher cipher = Cipher.getInstance(AlgoCryptage.DES.getAlgo());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] messageInBytes = messageToEncrypt.getBytes();
		return cipher.doFinal(messageInBytes);
	}
	
	public static final String decrypt(byte[] messageToDecrpyt, SecretKey key) 
		throws NoSuchAlgorithmException, 
		NoSuchPaddingException, 
		InvalidKeyException, 
		IllegalBlockSizeException, 
		BadPaddingException, 
		UnsupportedEncodingException {
		
		Cipher cipher = Cipher.getInstance(AlgoCryptage.DES.getAlgo());
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] messageInBytes = cipher.doFinal(messageToDecrpyt);
		return new String(messageInBytes, "UTF-8");
	}
}
