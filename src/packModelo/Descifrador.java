package packModelo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
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
	private String cI;
	
	public Descifrador(){
		
	}
	
	public void setAlgoritmo(String pA){
		this.algoritmo=pA;
	}
	
	public String getAlgoritmo(){
		return this.algoritmo;
	}
	
	public String encriptar(String key, String iv, String cleartext, String alg) throws Exception {
  
		this.setAlgoritmo(alg);
		
		if(getAlgoritmo().equals("AES")){
        	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
            return new String(encodeBase64(encrypted));
        }
        else if(getAlgoritmo().equals("DES")){
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
        else if(getAlgoritmo().equals("RSA")){
            // Generar el par de claves
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
        	// Obtener la clase para encriptar/desencriptar
            Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // Texto a encriptar
            String text = "Text to encrypt";

            // Se encripta
            rsa.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encriptado = rsa.doFinal(text.getBytes());

            // Escribimos el encriptado para verlo, con caracteres visibles
            for (byte b : encriptado) {
               System.out.print(Integer.toHexString(0xFF & b));
            }
            System.out.println();
//        	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
            return new String(encodeBase64(encriptado));
        }
        else if(getAlgoritmo().equals("MD5")){
        	
        	MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            md.update("texto a cifrar".getBytes());
            byte[] digest = md.digest();

            // Se escribe byte a byte en hexadecimal
            for (byte b : digest) {
               System.out.print(Integer.toHexString(0xFF & b));
            }
            System.out.println();

            // Se escribe codificado base 64. Se necesita la librería
            // commons-codec-x.x.x.jar de Apache
            byte[] encoded = encodeBase64(digest);
        	return encoded.toString();
        	
//        	Cipher cipher = Cipher.getInstance("MD5");
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
//            return new String(encodeBase64(encrypted));
        }
        else if(getAlgoritmo().equals("SHA")){
        	
        	MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
            md.update("texto a cifrar".getBytes());
            byte[] digest = md.digest();

            // Se escribe byte a byte en hexadecimal
            for (byte b : digest) {
               System.out.print(Integer.toHexString(0xFF & b));
            }
            System.out.println();

            // Se escribe codificado base 64. Se necesita la librería
            // commons-codec-x.x.x.jar de Apache
            byte[] encoded = encodeBase64(digest);
        	return encoded.toString();
        	
        	//Cipher cipher = Cipher.getInstance("SHA");
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), getAlgoritmo());
            //IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            //cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            //byte[] encrypted = cipher.doFinal(cleartext.getBytes());
            //return new String(encodeBase64(encrypted));
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
	
	public String ataqueFuerzaBruta(String txtCifrado,String alg){
		this.setAlgoritmo(alg);
		
		int l=txtCifrado.length();
		int cont=0;
		char c;
		String result="";
		String txtNoCifrado="";
		while(cont<=l){
			
			c=txtCifrado.charAt(cont);
			
			
			
			if(alg.equals("AES")){
				
			}
			else if(alg.equals("DES")){
				
			}
			else if(alg.equals("RSA")){
				
			}
			else if(alg.equals("SHA")){
				
			}
			else if(alg.equals("MD5")){
				
			}
			
			cont++;
		}
		return "a";
	}
	
	public String ataquePorFuerzaBruta(String txtEnc){
		String txtObt="";
		//Encriptar cada caracter UNICODE. Guardar el caracter encriptado en txtObt. Guardar el caracter actual en result.
		//En caso de encontrar los carcateres encriptados sean igual a los carcteres de txtEnc.
		//Salir del bucle devolver result.
		String result="";
		int i=0;
		char c=1;
//		while(c<=65535){
//			System.out.println(c);
//			c++;
//		}
		
		while(!txtEnc.equals(txtObt)){
			i=1;
			while(i<=txtEnc.length()){
				if(i==1){
					
				}
				else{
					
				}
				i++;
			}
		}
		return "a";
	}
}
