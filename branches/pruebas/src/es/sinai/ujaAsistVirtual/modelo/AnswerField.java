/**
 * Answer.java
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
public class AnswerField extends DocumentField {
	
	private final String FIELD = "<Answer>";
	
	private final String FIELD2 = "</Answer>";

	
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
		content = buffer.replace(FIELD, "");
		while(!buffer.contains(FIELD2)) {
			buffer = file.readLine();
			content += " " + buffer;
		}
		content += buffer;
		content = content.replace(FIELD2, "");
		content = content.replaceAll("\\n+", " ");
		content = content.replaceAll("\\s+", " ");
	}
}
