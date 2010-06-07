/**
 * AnalyzerFactory.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public interface FactoryAnalyzer {

	
	public Analyzer createAnalyzer() throws IOException;
}
