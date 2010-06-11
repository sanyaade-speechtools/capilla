/**
 * ValidAnalyzer.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public enum ValidAnalyzer {

	STANDARD,
	
	SNOWBALL,
	
	SIMPLE,
	
	SPANISH;
	
	public String toString() {
		
		
		switch(this) {
			case STANDARD: {
				
				return("org.apache.lucene.analysis.standard.StandardAnalyzer/nombreFactoria");
			}
			
			case SNOWBALL:{
				return("org.apache.lucene.analysis.snowball.SnowballAnalyzer/nombreFactoria");
			}
			
			case SIMPLE:{
				return("org.apache.lucene.analysis.Analyzer/nombreFactoria");
			}
			
			case SPANISH:{
				return("org.tartarus.snowball.ext.SpanishStemmer/nombreFactoria");
			}
			
			default:return(null);
		}
	}
	
}
