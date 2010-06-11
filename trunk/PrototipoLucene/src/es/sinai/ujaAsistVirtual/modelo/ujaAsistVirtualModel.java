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
import org.apache.lucene.document.Field;

import es.sinai.ujaAsistVirtual.exceptions.NoDirectorio;
import es.sinai.ujaAsistVirtual.exceptions.NoDirectorioNoLeer;

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
		if(find) 
			return (anaFactory.createAnalyzer());
		else
			return(null);
		
	}
	
	//Esta función debería de ponerse en otro sitio. Hay q pensarlo
	private Vector<DocumentField> getVectorFields() {
		Vector<DocumentField> vFields = new Vector<DocumentField>();
		
		String name, index, store, termVector;
		
		//Campo categoria
		name = ConfigurationFile.getPropetiesValue(PropertiesName.CATEGORIE_NAME);
		index = ConfigurationFile.getPropetiesValue(PropertiesName.CATEGORIE_INDEX);
		store = ConfigurationFile.getPropetiesValue(PropertiesName.CATEGORIE_STORE);
		termVector = ConfigurationFile.getPropetiesValue(PropertiesName.CATEGORIE_TERM_VECTOR);
		
		DocumentField field = new CategorieField();
		field.setName(name);
		field.setIndex(Field.Index.valueOf(index));
		field.setStore(Field.Store.valueOf(store));
		field.setTermVector(Field.TermVector.valueOf(termVector));
		vFields.add(field);
		
		//Campo pregunta
		name = ConfigurationFile.getPropetiesValue(PropertiesName.QUESTION_NAME);
		index = ConfigurationFile.getPropetiesValue(PropertiesName.QUESTION_INDEX);
		store = ConfigurationFile.getPropetiesValue(PropertiesName.QUESTION_STORE);
		termVector = ConfigurationFile.getPropetiesValue(PropertiesName.QUESTION_TERM_VECTOR);
		
		field = new QuestionField();
		field.setName(name);
		field.setIndex(Field.Index.valueOf(index));
		field.setStore(Field.Store.valueOf(store));
		field.setTermVector(Field.TermVector.valueOf(termVector));
		vFields.add(field);
		
		
		//Campo AnswerIndex
		name = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWERINDEX_NAME);
		index = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWERINDEX_INDEX);
		store = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWERINDEX_STORE);
		termVector = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWERINDEX_TERM_VECTOR);
		
		field = new AnswerIndexField();
		field.setName(name);
		field.setIndex(Field.Index.valueOf(index));
		field.setStore(Field.Store.valueOf(store));
		field.setTermVector(Field.TermVector.valueOf(termVector));
		vFields.add(field);
		
		//Campo Answer
		name = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWER_NAME);
		index = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWER_INDEX);
		store = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWER_STORE);
		termVector = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWER_TERM_VECTOR);
		
		field = new AnswerField();
		field.setName(name);
		field.setIndex(Field.Index.valueOf(index));
		field.setStore(Field.Store.valueOf(store));
		field.setTermVector(Field.TermVector.valueOf(termVector));
		vFields.add(field);
		
		//Campo Url
		name = ConfigurationFile.getPropetiesValue(PropertiesName.URL_NAME);
		index = ConfigurationFile.getPropetiesValue(PropertiesName.URL_INDEX);
		store = ConfigurationFile.getPropetiesValue(PropertiesName.URL_STORE);
		termVector = ConfigurationFile.getPropetiesValue(PropertiesName.URL_TERM_VECTOR);
		
		field = new UrlField();
		field.setName(name);
		field.setIndex(Field.Index.valueOf(index));
		field.setStore(Field.Store.valueOf(store));
		field.setTermVector(Field.TermVector.valueOf(termVector));
		vFields.add(field);
		
		return(vFields);
	}
	
	public ujaAsistVirtualModel (String aPathConfigurationFile,
			String aPathFiles, String aPathIndex) throws FileNotFoundException,
			IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoDirectorioNoLeer, NoDirectorio {
		
		//CREAR EL ANALIZADOR
		Analyzer analyzer = selectAnalyzer();
		
		//CREAR EL VECTOR DE  CAMPOS
		
		Vector<DocumentField> categories = getVectorFields();
		
		
		
		index = new indexFiles(analyzer,aPathFiles,aPathIndex,categories);
		
		index.indexDocs();
	}
}
