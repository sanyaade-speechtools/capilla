/**
 * SimpleAnalyzer.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class FactorySimpleAnalyzer implements FactoryAnalyzer {

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.modelo.AnalyzerFactory#createAnalyzer()
	 */
	@Override
	public Analyzer createAnalyzer() throws IOException {
		return (new SimpleAnalyzer());
	}

}
