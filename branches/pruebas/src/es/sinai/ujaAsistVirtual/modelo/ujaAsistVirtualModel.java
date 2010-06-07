/**
 * ujaAsistVirtualModel.java
 * @author Eugenio Martínez Cámara
 * @date 07/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.apache.lucene.analysis.Analyzer;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class ujaAsistVirtualModel {

	//Atributos
	
	private indexFiles index;
	
	
	private Analyzer selectAnalyzer() throws ClassNotFoundException,
		InstantiationException, IllegalAccessException, IOException{
		
		String propAna = ConfigurationFile.getPropetiesValue(PropertiesName.ANALYZER);
		ValidAnalyzer[] nValidAnalyzer = ValidAnalyzer.class.getEnumConstants();
		FactoryAnalyzer anaFactory = null;
		Boolean find = false;
		int i = 0;
		int tam = nValidAnalyzer.length;
		while((i < tam) && (!find)) {
			String[] names = nValidAnalyzer[i].toString().split("/");
			if(names[1].contains(propAna)) {
				Class<?> factory = Class.forName(names[2]);
				anaFactory = (FactoryAnalyzer) factory.newInstance();
				find = true;
			}
		}
		if(find) //TODAVÍA NO ESTÁ TERMINADO
			return (anaFactory.createAnalyzer());
		else
			return(null);
		
	}
	
	private Vector<DocumentField> getVectorFields() {
		Vector<DocumentField> vFields = new Vector<DocumentField>();
		
		CategorieField cf = new CategorieField();
		
		return(vFields);
	}
	
	public ujaAsistVirtualModel (String aPathConfigurationFile,
			String aPathFiles, String aPathIndex) throws FileNotFoundException,
			IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		
		//CREAR EL ANALIZADOR
		Analyzer analyzer = selectAnalyzer();
		
		//CREAR EL VECTOR DE  CAMPOS
		
		
		
		index = new indexFiles(); //NO VALE EL POR DEFECTO
	}
}
