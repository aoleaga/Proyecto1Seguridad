package packModelo;

public class Main{
		 
		 public static void main(String[] args) throws Exception {
			String key = "92AE31A79FEEB2A3"; //llave
		 	String iv = "0123456789ABCDEF"; // vector de inicialización
		 	String cleartext = "hola";
		 	Descifrador d= new Descifrador();
		 	System.out.println("Texto encriptado AES: "+d.encriptar(key, iv,cleartext,"AES"));
		 	System.out.println("Texto encriptado: "+d.encriptar(key, iv,cleartext,"RSA"));
		 	System.out.println("Texto encriptado MD5: "+d.encriptar(key, iv,cleartext,"MD5"));
		 	System.out.println("Texto encriptado SHA: "+d.encriptar(key, iv,cleartext,"SHA"));
		 	System.out.println("Texto encriptado DES: "+d.encriptar(key, iv,cleartext,"DES"));

		 }
		 		
}
