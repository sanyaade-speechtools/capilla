/**
 * AppUjaAsistVirtual.java
 * @author Eugenio Martínez Cámara
 * @date 10/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.vista;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class AppUjaAsistVirtual {

	
	
	
//Atributos
	
	private String[] args;
	
	
	public AppUjaAsistVirtual (String[] aArgs) {
		args = aArgs;
	}
	
	
	
	public void execApp() {
		
		if(args[0].equals(ValidInputArguments.INDEX1.toString())) {
			
		} else if(args[0].contains(ValidInputArguments.INDEX2.toString())) {
			
		} else {
			
		}
		
		if(args[1].equals(ValidInputArguments.SEARCH1.toString())) {
			
		} else if(args[1].contains(ValidInputArguments.SEARCH2.toString())) {
			
		} else {
			
		}
	}
}
