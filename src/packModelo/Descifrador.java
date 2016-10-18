package packModelo;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class Descifrador {
	private String algoritmo;
	private ArrayList<String> palabras;
	
	public Descifrador(){
		palabras=new ArrayList<String>();
	}
	
	public void setAlgoritmo(String pA){
		this.algoritmo=pA;
	}
	
	public String getAlgoritmo(){
		return this.algoritmo;
	}
	
	public String md5(String cleartext) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        md.update(cleartext.getBytes());
        byte[] digest = md.digest();
        String[] hexa=null;
        int i=0;
        // Se escribe byte a byte en hexadecimal
        for (byte b : digest) {
           System.out.print(Integer.toHexString(0xFF & b));
        }
        // Se escribe codificado base 64. Se necesita la librería
        // commons-codec-x.x.x.jar de Apache
        byte[] encoded = encodeBase64(digest);
    	return new String(encoded);
    }
	
	public String aes(String key, String iv, String cleartext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
        return new String(encodeBase64(encrypted));
	}
	
	public String sha(String cleartext) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
        md.update(cleartext.getBytes());
        byte[] digest = md.digest();

        // Se escribe byte a byte en hexadecimal
        for (byte b : digest) {
           System.out.print(Integer.toHexString(0xFF & b));
        }
        // Se escribe codificado base 64. Se necesita la librería
        // commons-codec-x.x.x.jar de Apache
        byte[] encoded = encodeBase64(digest);
    	return new String(encoded);
	}
	
	public String des(String key, String iv, String cleartext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("DES");
        //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKeySpec kspec = new DESKeySpec(key.getBytes());
        SecretKey ks = skf.generateSecret(kspec);
        
        cipher.init(Cipher.ENCRYPT_MODE, ks);
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
        return new String(encodeBase64(encrypted));
	}
	
	public String rsa(String cleartext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		 // Generar el par de claves
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
    	// Obtener la clase para encriptar/desencriptar
        Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Se encripta
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal(cleartext.getBytes());

        // Escribimos el encriptado para verlo, con caracteres visibles
        for (byte b : encriptado) {
           System.out.print(Integer.toHexString(0xFF & b));
        }
        return new String(encriptado);
	}
	
	public String encriptarSC(String cleartext) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		if(getAlgoritmo().equals("MD5")){
        	return this.md5(cleartext);
        }
        else if(getAlgoritmo().equals("SHA")){
        	return this.sha(cleartext);
        }
        else if(getAlgoritmo().equals("RSA")){
        	return this.rsa(cleartext);
        }
        else{
        	return "No existe";
        }
	}
	
	public String encriptar(String key, String iv, String cleartext, String alg) throws Exception {
  
		this.setAlgoritmo(alg);
		
		if(getAlgoritmo().equals("AES")){
        	return this.aes(key, iv, cleartext);
        }
        else if(getAlgoritmo().equals("DES")){
        	return this.des(key, iv, cleartext);
        }
        else if(getAlgoritmo().equals("RSA")){
            return this.rsa(cleartext);
        }
        else if(getAlgoritmo().equals("MD5")){
        	return this.md5(cleartext);
        }
        else if(getAlgoritmo().equals("SHA")){
        	return this.sha(cleartext);
        }
        else{
        	return "No existe";
        }
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
//        return new String(encodeBase64(encrypted));
}
	
	public String ataqueClaveFB(String txtCifrado,String alg){
		this.setAlgoritmo(alg);
		
		int l=txtCifrado.length();
		int cont=0;
		char c;
		String result="";
		String txtNoCifrado="";
		while(cont<=l){
			
			c=txtCifrado.charAt(cont);
			cont++;
		}
		return "a";
	}
	
	public String ataqueTextoFB(String txtEnc, String alg) throws Exception{
		this.setAlgoritmo(alg);
		String palabra="";
		String result="";
		int i=1;
		boolean salir=false;
		System.out.println(txtEnc.length());
		while(i<=txtEnc.length()&&!salir){
			System.out.println("----------------------------"+i+"-------------------------");
			 char[] chars = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
			 char[] charsM = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
			 this.iterate(chars, i, new char[i], 0);
			 this.iterate(charsM, i, new char[i], 0);
			 Iterator<String> it=this.palabras.iterator();
			 while(it.hasNext()&&!salir){
				palabra=it.next();
				result=this.encriptarSC(palabra);
				if(result.equals(txtEnc)){
					salir=true;
				}
			 }
			 i++;
		}
		return palabra;
	}
	
    public void iterate(char[] chars, int len, char[] build, int pos){
        if (pos == len) {
            String word = new String(build);
            // do what you need with each word here
            palabras.add(word);
            System.out.println(word);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            build[pos] = chars[i];
            iterate(chars, len, build, pos + 1);
        }
    }
}
