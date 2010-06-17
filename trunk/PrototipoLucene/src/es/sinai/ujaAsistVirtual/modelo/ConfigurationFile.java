/**
 * ConfigurationFile.java
 * @author Eugenio Martínez Cámara
 * @date 06/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class ConfigurationFile {
	
	//Atributos
	
	private static ConfigurationFile singleton = new ConfigurationFile();
	
	private Properties properties = null;
	
	//--------------------------------------------------------------------------
	
	//Métodos
	


	
	
	public static  void readProperties(String pathFile) throws FileNotFoundException, IOException {
		singleton.properties = new Properties();
		singleton.properties.load(new FileInputStream(new File(pathFile)));
	}
	
	public static String getPropetiesValue(PropertiesName pn) {
		return(singleton.properties.getProperty(pn.toString()));
	}
	
	public static String[] getPropertiesWithMultipleValues(PropertiesName pn) {
		return(singleton.properties.getProperty(pn.toString()).split(","));
	}
}
