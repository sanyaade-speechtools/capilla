/**
 * CategorieField.java
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
public class CategorieField extends DocumentField {
	
	//CONSTANSTES
	
	private final String FIELD = "<Categorie Text=";
	
	private final String REGEX_FIELD = "<Categorie Text=\"(.+)\">";
	
	//--------------------------------------------------------------------------

	/**
	 * @return El contenido del campo o null si el buffer no corresponde con la
	 * definición del campo.
	 * @see es.sinai.ujaAsistVirtual.modelo.DocumentField#parse(java.io.Reader)
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


	@Override
	public boolean isThisField(String buffer) {
		return (buffer.contains(FIELD));
	}

}
