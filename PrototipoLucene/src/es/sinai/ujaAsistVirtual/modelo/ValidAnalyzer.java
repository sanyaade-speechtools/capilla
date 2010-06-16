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
				
				return("org.apache.lucene.analysis.standard.StandardAnalyzer/es.sinai.ujaAsistVirtual..modelo.FactoryStandardAnalyzer");
			}
			
			case SNOWBALL:{
				return("org.apache.lucene.analysis.snowball.SnowballAnalyzer/es.sinai.ujaAsistVirtual.modelo.FactorySnowballAnalyzer");
			}
			
			case SIMPLE:{
				return("org.apache.lucene.analysis.SimpleAnalyzer/es.sinai.ujaAsistVirtual.modelo.SimpleAnalyzer");
			}
			
			case SPANISH:{
				return("org.tartarus.snowball.ext.SpanishStemmer/org.tartarus.snowball.ext.SpanishStemmer");
			}
			
			default:return(null);
		}
	}
	
}
