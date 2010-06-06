/**
 * PropertiesName.java
 * @author Eugenio Martínez Cámara
 * @date 06/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public enum PropertiesName {

	/**
	 * Nombre de la clase del analizador que utilizará el indexador.
	 */
	ANALYZER,
	
	/**
	 * Campo que índica si la categoría debe ser almacenada o no.
	 */
	CATEGORIE_STORE,
	
	/**
	 * Campo que indica si la categoría debe indexarse o no.
	 */
	CATEGORIE_INDEX,
	
	/**
	 * Campo que indica si la categoría debe de tener Term Vectors 
	 */
	CATEGORIE_TERM_VECTOR,
	
	/**
	 * Campo que índica si la pregunta debe ser almacenada o no.
	 */
	QUESTION_STORE,
	
	/**
	 * Campo que indica si la pregunta debe indexarse o no.
	 */
	QUESTION_INDEX,
	
	/**
	 * Campo que indica si la pregunta debe de tener Term Vectors 
	 */
	QUESTION_TERM_VECTOR,
	
	/**
	 * Campo que índica si answerindex debe ser almacenada o no.
	 */
	ANSWERINDEX_STORE,
	
	/**
	 * Campo que indica si la answerindex debe indexarse o no.
	 */
	ANSWERINDEX_INDEX,
	
	/**
	 * Campo que indica si la pregunta debe de tener Term Vectors 
	 */
	ANSWERINDEX_TERM_VECTOR,
	
	/**
	 * Campo que índica si la respuesta debe ser almacenada o no.
	 */
	ANSWER_STORE,
	
	/**
	 * Campo que indica si la respuesta debe indexarse o no.
	 */
	ANSWER_INDEX,
	
	/**
	 * Campo que indica si la pregunta debe de tener Term Vectors 
	 */
	ANSWER_TERM_VECTOR,
	
	/**
	 * Campo que indica la url de la web donde se ha cogido la información.
	 */
	URL;
	
	public String toString() {
		switch(this) {
			case ANALYZER:return("analyzer");
			
			case CATEGORIE_STORE:return("categorie.store");
			
			case CATEGORIE_INDEX:return("categorie.index");
			
			case CATEGORIE_TERM_VECTOR:return("categorie.term_vector");
			
			case ANSWERINDEX_STORE:return("answerindex.store");
			
			case ANSWERINDEX_INDEX:return("answerindex.index");
			
			case ANSWERINDEX_TERM_VECTOR:return("answerindex.term_vector");
			
			case ANSWER_STORE:return("answer.store");
			
			case ANSWER_INDEX:return("answer.index");
			
			case ANSWER_TERM_VECTOR:return("answer.term_vector");
			
			case URL:return("url");
			
			default:return(null);
			
		}
	}
}
