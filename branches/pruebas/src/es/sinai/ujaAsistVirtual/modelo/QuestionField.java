/**
 * Question.java
 * @author Eugenio Martínez Cámara
 * @date 03/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Eugenio Martínez Cámara
 *
 */
public class QuestionField extends DocumentField {

	private final String FIELD = "<Question Text=";
	
	private final String REGEX_FIELD = "<Question Text=\"(.+)\"";
	


	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#isThisField(java.lang.String)
	 */
	@Override
	public boolean isThisField(String buffer) {
		
		return (buffer.contains(FIELD));
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#parse(java.io.Reader, java.lang.String)
	 */
	@Override
	public void parse(BufferedReader file, String buffer) {
		Pattern pat = Pattern.compile(REGEX_FIELD);
		Matcher mat = pat.matcher(buffer);
		content = null;
		if(mat.find()) {
			content = mat.group(1).trim();
			content = content.replaceAll("\\s+"," ");
		}
	}

}
