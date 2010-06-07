/**
 * FactoryStandarAnalyzer.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class FactoryStandardAnalyzer implements FactoryAnalyzer {

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.modelo.AnalyzerFactory#createAnalyzer()
	 */
	@Override
	public Analyzer createAnalyzer() throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30,
				new FileReader(ConfigurationFile.getPropetiesValue(PropertiesName.PATH_STOPPER_FILE)));
		
		return (analyzer);
	}

}
