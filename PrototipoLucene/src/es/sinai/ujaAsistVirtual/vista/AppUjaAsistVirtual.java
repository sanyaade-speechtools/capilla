/**
 * AppUjaAsistVirtual.java
 * @author Eugenio Martínez Cámara
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
 * @author Eugenio Martínez Cámara
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
		System.out.println("Resultado: " + id + ":");
		String fieldName = ConfigurationFile.getPropetiesValue(PropertiesName.QUESTION_NAME);
		System.out.println("\tPregunta: " + doc.get(fieldName));
		fieldName = ConfigurationFile.getPropetiesValue(PropertiesName.ANSWER_NAME);
		System.out.println("\tRespuesta: " + doc.get(fieldName));
		fieldName = ConfigurationFile.getPropetiesValue(PropertiesName.URL_NAME);
		System.out.println("\tUrl: " + doc.get(fieldName));
		
	}
	
	private void printExplanation(int id) {
		Explanation expResult = modelo.getSearch().getExplicationResults()[id];
		System.out.print("Detalle resultado: ");
		System.out.println(expResult.toString());
	}
	
	private void search() throws Exception {
		modelo.initSearchDocs();
		printHeaderSearch();
		System.out.println("Buscar (NO para salir): ");
		Scanner sc = new Scanner(System.in);
		String userQuery = sc.nextLine();
		while (!userQuery.equals("NO")) {
			modelo.searchDocs(userQuery);
			printSearchResultsHeader();
			for(int i = 0; i < modelo.getSearch().getResult().scoreDocs.length; i++) {
				printSearchResult(i);
				if(ConfigurationFile.getPropetiesValue(PropertiesName.SEARCH_EXPLANATION).equals("YES")) {
					printExplanation(i);
				}
			}
			System.out.print("Buscar (NO para salir): ");
			userQuery = sc.nextLine();
		}
		sc.close();
		modelo.getSearch().close();
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
