/**
 * AnalyzerFactory.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import org.apache.lucene.analysis.Analyzer;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public interface AnalyzerFactory {

	
	public Analyzer createAnalyzer();
}
