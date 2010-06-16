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
	 * Lenguaje en el que están escritos los documentos de la colección.
	 * Se debe de poner en inglés. Por ejemplo si los documentos están escritos
	 * en español, el valor del campo tiene que ser spanish. 
	 */
	LANGUAGE,
	
	/**
	 * Ruta del fichero en el que se encuentra la stooper list.
	 */
	PATH_STOPPER_FILE,
	
	/**
	 * Ruta de los ficheros a indexar
	 */
	PATH_FILES_TO_INDEX,
	
	/**
	 * Ruta de salida del �ndice.
	 */
	PATH_OUTPUT_INDEX,
	
	CATEGORIE_NAME,
	
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
	
	QUESTION_NAME,
	
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
	
	ANSWERINDEX_NAME,
	
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
	
	ANSWER_NAME,
	
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
	URL_NAME,
	
	URL_STORE,
	
	URL_INDEX,
	
	URL_TERM_VECTOR,
	
	/**
	 * 
	 */
	SEARCH_ANALYZER,
	
	SEARCH_FIELD,
	
	SEARCH_INDEX,
	
	SEARCH_N_RESULTS,
	
	SEARCH_EXPLANATION;
	
	public String toString() {
		switch(this) {
			case ANALYZER:return("analyzer");
			
			case LANGUAGE:return("language");
			
			case PATH_STOPPER_FILE:return("path_stopper");
			
			case PATH_FILES_TO_INDEX:return("path_files_to_index");
			
			case PATH_OUTPUT_INDEX: return("path_output_index");
			
			case CATEGORIE_NAME:return("categorie.name");
			
			case CATEGORIE_STORE:return("categorie.store");
			
			case CATEGORIE_INDEX:return("categorie.index");
			
			case CATEGORIE_TERM_VECTOR:return("categorie.term_vector");
			
			case QUESTION_NAME:return("question.name");
			
			case QUESTION_STORE:return("question.store");
			
			case QUESTION_INDEX:return("question.index");
			
			case QUESTION_TERM_VECTOR:return("question.term_vector");
			
			case ANSWERINDEX_NAME:return("answerIndex.name");
			
			case ANSWERINDEX_STORE:return("answerindex.store");
			
			case ANSWERINDEX_INDEX:return("answerindex.index");
			
			case ANSWERINDEX_TERM_VECTOR:return("answerindex.term_vector");
			
			case ANSWER_NAME:return("answer.name");
			
			case ANSWER_STORE:return("answer.store");
			
			case ANSWER_INDEX:return("answer.index");
			
			case ANSWER_TERM_VECTOR:return("answer.term_vector");
			
			case URL_NAME:return("url.name");
			
			case URL_STORE:return("url.store");
			
			case URL_INDEX:return("url.index");
			
			case URL_TERM_VECTOR:return("url.term_vector");
			
			case SEARCH_ANALYZER:{return("search_analyzer");}
			
			case SEARCH_FIELD:return("search_field");
			
			case SEARCH_INDEX:return("search_index");
			
			case SEARCH_N_RESULTS:return("search_n_results");
			
			case SEARCH_EXPLANATION:return("search_explanation");
			
			default:return(null);
			
		}
	}
}
