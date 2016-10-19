package packModelo;

public class Main{
		 
		 public static void main(String[] args) throws Exception {
			String key = "92AE31A79FEEB2A3"; //llave
		 	String iv = "0123456789ABCDEF"; // vector de inicialización
		 	String cleartext = "hola";
		 	Descifrador d= new Descifrador();
		 	System.out.println("Text hola a todos base64 "+d.base64("hola a todos"));
		 	if(d.base64("hola a todos").equals("aG9sYSBhIHRvZG9z")){
		 		System.out.println("FUNCIONAAAAAAAAAAAAAAAAAA");
		 	}

//		 	System.out.println("TEXTO ENCRIPTADO hola MD5:"+d.ataqueTextoFB("4d186321c1a7f0f354b297e8914ab240", "MD5")+"\n");
//		 	System.out.println("TEXTO ENCRIPTADO hola SHA:"+d.ataqueTextoFB("99800b85d3383e3a2fb45eb7d0066a4879a9dad0", "SHA")+"\n");

//		 	System.out.println("TEXTO ENCRIPTADO hola a todos MD5:"+d.ataqueTextoFB("a88cedaca6edf1c49cb3d127c5e9517d", "MD5")+"\n");
//		 	if(d.md5("a").equals("0cc175b9c0f1b6a831c399e269772661")){
//		 		System.out.println("FUNCIONAAAAAAAAAAAA");
//		 	}
//		 	System.out.println("TEXTO ENCRIPTADO a MD5:"+d.ataqueTextoFB("0cc175b9c0f1b6a831c399e269772661", "MD5")+"\n");
//		 	System.out.println("TEXTO ENCRIPTADO a SHA:"+d.ataqueTextoFB("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", "SHA"+"\n"));

		 	
//		 	System.out.println("Text i SHA "+d.sha("i"));
//		 	System.out.println("Text s SHA "+d.sha("s"));
//		 	if(d.sha("s").equals("a0f1490a20d0211c997b44bc357e1972deab8ae3")){
//		 		System.out.println("FUNCIONAAAAAAAA");
//		 	}
//		 	System.out.println("042dc4512fa3d391c5170cf3aa61e6a638f84342".length());
//		 	System.out.println("TEXTO ENCRIPTADO i SHA:"+d.ataqueTextoFB("042dc4512fa3d391c5170cf3aa61e6a638f84342", "SHA"));

//		 	System.out.println("Texto encriptado de a es 0cc175b9c0f1b6a831c399e269772661 y el programa dice que es:"+d.md5("a"));
//		 	System.out.println("Texto encriptado de a es 03c7c0ace395d80182db07ae2c30f034 y el programa dice que es:"+d.md5("s"));
//		 	System.out.println("Texto encriptado AES: "+d.encriptar(key, iv,cleartext,"AES"));
//		 	System.out.println("Texto encriptado RSA: "+d.encriptar(key, iv,cleartext,"RSA"));
//		 	System.out.println("Texto encriptado MD5: "+d.encriptar(key, iv,cleartext,"MD5"));
//		 	System.out.println("Texto encriptado SHA: "+d.encriptar(key, iv,cleartext,"SHA"));
//		 	System.out.println("Texto encriptado DES: "+d.encriptar(key, iv,cleartext,"DES"));

		 }
		 		
}
