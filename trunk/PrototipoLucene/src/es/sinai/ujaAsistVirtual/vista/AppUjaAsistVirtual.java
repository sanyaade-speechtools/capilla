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

import es.sinai.ujaAsistVirtual.exceptions.NoDirectorio;
import es.sinai.ujaAsistVirtual.exceptions.NoDirectorioNoLeer;
import es.sinai.ujaAsistVirtual.modelo.ConfigurationFile;
import es.sinai.ujaAsistVirtual.modelo.ujaAsistVirtualModel;

/**
 * @author Eugenio MartÃ­nez CÃ¡mara
 *
 */
public class AppUjaAsistVirtual {

	
	
	
//Atributos
	
	private String[] args;
	
	private ujaAsistVirtualModel modelo;
	
	
	public AppUjaAsistVirtual (String[] aArgs) throws Exception  {
		args = aArgs;
		
		modelo = new ujaAsistVirtualModel();
	}
	
	private void loadProperties(String configurationFile) throws FileNotFoundException, IOException {
		ConfigurationFile.readProperties(configurationFile);
	}
	
	private void index() throws Exception {
		System.out.println("\nComienza la indexación...\n");
		modelo.initSearchDocs();
		modelo.indexDocs();
		System.out.println("\nFin de la indexación...\n");
	}
	
	private void printHeaderSearch() {
		System.out.println("\nBienvenido al prototipo de motor de búsqueda de un asistente virtual");
		System.out.println("----------------------------------------------------------------------\n");
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
		}
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
				
			} else if(args[0].contains(ValidInputArguments.SEARCH2.toString())) {
				loadProperties(args[1].split("=")[1]);
			} else {
			
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
