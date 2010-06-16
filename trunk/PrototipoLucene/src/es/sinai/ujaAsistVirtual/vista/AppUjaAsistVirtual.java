/**
 * AppUjaAsistVirtual.java
 * @author Eugenio MartÃ­nez CÃ¡mara
 * @date 10/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.vista;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.ScoreDoc;
import es.sinai.ujaAsistVirtual.modelo.ConfigurationFile;
import es.sinai.ujaAsistVirtual.modelo.PropertiesName;
import es.sinai.ujaAsistVirtual.modelo.ujaAsistVirtualModel;

/**
 * @author Eugenio MartÃ­nez CÃ¡mara
 *
 */
public class AppUjaAsistVirtual {

	
	
	
//Atributos
	
	private String[] args;
	
	private ujaAsistVirtualModel modelo;
	
	
	public AppUjaAsistVirtual (String[] aArgs) {
		args = aArgs;
		
		modelo = new ujaAsistVirtualModel();
	}
	
	private void loadProperties(String configurationFile) throws FileNotFoundException, IOException {
		ConfigurationFile.readProperties(configurationFile);
	}
	
	private void index() throws Exception {
		System.out.println("\nComienza la indexación...\n");
		modelo.initIndexDocs();
		modelo.indexDocs();
		System.out.println("\nFin de la indexación...\n");
	}
	
	private void printHeaderSearch() {
		System.out.println("\nBienvenido al prototipo de motor de búsqueda de un asistente virtual");
		System.out.println("----------------------------------------------------------------------\n");
	}
	
	private void printSearchResultsHeader() {
		System.out.println("\nLos resultados de la búsqueda son:");
		System.out.println("----------------------------------------------------------------------\n");
	}
	
	
	
	private void printSearchResult(int id) throws CorruptIndexException, IOException {
		
		ScoreDoc match = modelo.getSearch().getResult().scoreDocs[id];
		Document doc = modelo.getSearch().getDocument(match.doc);
		System.out.println("----------------------------------------------------------------------\n");
		System.out.println("Resultado: " + id + ":");
		System.out.println(doc.get("\tPregunta: " + doc.get(PropertiesName.QUESTION_NAME.toString())));
		System.out.println(doc.get("\tRespuesta: " + doc.get(PropertiesName.ANSWER_NAME.toString())));
		
	}
	
	private void printExplanation() {
		Explanation[] expResults = modelo.getSearch().getExplicationResults();
		System.out.println("Detalle resultado: ");
		System.out.println(expResults.toString());
	}
	
	private void search() throws Exception {
		modelo.initSearchDocs();
		printHeaderSearch();
		String userQuery = "";
		while (!userQuery.equals("NO")) {
			System.out.println("Buscar (NO para salir): ");
			Scanner sc = new Scanner(System.in);
			userQuery = sc.nextLine();
			modelo.searchDocs(userQuery);
			for(int i = 0; i < modelo.getSearch().getResult().totalHits; i++) {
				printSearchResultsHeader();
				printSearchResult(i+1);
				if(ConfigurationFile.getPropetiesValue(PropertiesName.SEARCH_EXPLANATION).equals("YES")) {
					printExplanation();
				}
			}
		}
	}
	
	private void ErrUse() {
		System.out.println("La forma de utilizar este prototipo es: ");
		System.out.println("Para indexar: PrototipoLucene -i <Ruta fichero configuración.>");
		System.out.println("Para buscar: PrototipoLucene -s <Ruta fichero configuración.>");
	}
	
	public void execApp() {
		try {
			if(args[0].equals(ValidInputArguments.INDEX1.toString())) {
				loadProperties(args[1]);
				index();
			} else if(args[0].contains(ValidInputArguments.INDEX2.toString())) {
				loadProperties(args[1].split("=")[1]);
				index();
			} else if(args[0].equals(ValidInputArguments.SEARCH1.toString())) {
				loadProperties(args[1]);
				search();
				
			} else if(args[0].contains(ValidInputArguments.SEARCH2.toString())) {
				loadProperties(args[1].split("=")[1]);
				search();
			} else {
				ErrUse();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
