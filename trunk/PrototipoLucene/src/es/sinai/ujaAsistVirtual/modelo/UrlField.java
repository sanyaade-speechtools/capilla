/**
 * UrlField.java
 * @author Eugenio Martínez Cámara
 * @date 04/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class UrlField extends DocumentField {

	private final String FIELD = "<Url>";
	
	private final String FIELD2 = "</Url>";
	
	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.modelo.DocumentField#isThisField(java.lang.String)
	 */
	@Override
	public boolean isThisField(String buffer) {
		return (buffer.contains(FIELD));
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.modelo.DocumentField#parse(java.io.BufferedReader, java.lang.String)
	 */
	@Override
	public void parse(BufferedReader file, String buffer) throws IOException {
		content = new String(buffer);
		while(!buffer.contains(FIELD2)) {
			buffer = file.readLine();
			content += buffer;
		}
		
		content = content.replace(FIELD, "");
		content = content.replace(FIELD2, "");
		content = content.replace("\\n+", "");
		content = content.replace("\\s+", "");
		content = content.trim();
	}

}
