/**
 * FactorySnowballAnalyzer.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.util.Version;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class FactorySnowballAnalyzer implements FactoryAnalyzer {

	
	
	private String getName(String language) {
		if(language.equals("english") || language.equals("English"))
			return("EnglishStemmer");
		else if(language.equals("spanish") || (language.equals("Spanish")))
			return("SpanishStemmer");
		else
			return (null);
	}
	
	private Set<String> loadStopList() throws IOException {
		Set<String> stopList = new HashSet<String>();
		String pathStopList = ConfigurationFile.getPropetiesValue(PropertiesName.PATH_STOPPER_FILE);
		BufferedReader bf = new BufferedReader(new FileReader(pathStopList));
		String word = null;
		while((word = bf.readLine()) != null) {
			stopList.add(word);
		}
		return(stopList);
	}
	
	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.modelo.AnalyzerFactory#createAnalyzer()
	 */
	@Override
	public Analyzer createAnalyzer() throws IOException {
		String language = ConfigurationFile.getPropetiesValue(PropertiesName.LANGUAGE);
		
		SnowballAnalyzer analyzer = new SnowballAnalyzer(Version.LUCENE_30,
											getName(language), loadStopList());
		return analyzer;
	}

}
