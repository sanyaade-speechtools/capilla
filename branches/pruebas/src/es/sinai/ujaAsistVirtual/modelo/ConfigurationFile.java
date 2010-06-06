/**
 * ConfigurationFile.java
 * @author Eugenio Martínez Cámara
 * @date 06/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.util.Properties;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class ConfigurationFile {
	
	//Atributos
	
	private static ConfigurationFile singleton = new ConfigurationFile();
	
	private Properties properties;
	
	//--------------------------------------------------------------------------
	
	//Métodos
	
	private ConfigurationFile () {
		properties = null;
	}
	
	private void readProperties(String pathFile) {
		properties = new Properties();
	}
}
