package packModelo;

public class Descifrador {
	
	public Descifrador(){
		
	}
	
	public String ataqueFuerzaBruta(String txtCifrado,String alg){
		int l=txtCifrado.length();
		int cont=0;
		char c;
		String result="";
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
