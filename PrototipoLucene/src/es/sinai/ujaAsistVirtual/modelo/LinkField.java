/**
 * @file LinkField.java;
 * @author geni
 * @date 24/06/2010
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * LinkField
 * @author geni
 * @version 1.0
 * @date 24/06/2010
 */
public class LinkField extends DocumentField {

	//Atributos
	private final String FIELD = "<Link>";
		
	private final String FIELD2 = "</Link>";
	
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
		buffer = buffer.replace(FIELD, "");
		buffer = buffer.replace(FIELD2, "");
		if(content == null)
			content = new String(buffer);
		else
			content += " " + buffer;
	}

}
