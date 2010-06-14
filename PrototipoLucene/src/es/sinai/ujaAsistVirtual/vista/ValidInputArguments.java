/**
 * ValidInputArguments.java
 * Creado: 14/06/2010
 * 
 */
package es.sinai.ujaAsistVirtual.vista;

/**
 * @author Eugenio Martínez Cámara
 * @version 1.0
 * @date 14/06/2010
 * @class ValidInputArguments
 * @package es.sinai.ujaAsistVirtual.vista
 *
 */
public enum ValidInputArguments {
	
	INDEX1,
	
	INDEX2,
	
	SEARCH1,
	
	SEARCH2;
	
	public String toString() {
		switch(this) {
			case INDEX1: return("-i");
			
			case INDEX2: return("index=");
			
			case SEARCH1: return("-s");
			
			case SEARCH2: return("search=");
			
			default: return(null);
		}
	}

}
