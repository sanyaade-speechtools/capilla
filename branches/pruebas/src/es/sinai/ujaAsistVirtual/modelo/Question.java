/**
 * Question.java
 * @author Eugenio Martínez Cámara
 * @date 03/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;

import es.sinai.ujaAsistVirtual.exceptions.DocumentField;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class Question extends DocumentField {

	private final String FIELD = "<Question Text=";
	
	private final String REGEX_FIELD = "<Question Text=\"(.+)\"";
	
	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#getIndex()
	 */
	@Override
	public Index getIndex() {
		
		return (index);
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#getStore()
	 */
	@Override
	public Store getStore() {
		
		return (store);
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#getTermVector()
	 */
	@Override
	public TermVector getTermVector() {

		return (termVector);
	}

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
	public String parse(Reader file, String buffer) {
		Pattern pat = Pattern.compile(REGEX_FIELD);
		Matcher mat = pat.matcher(buffer);
		String result = null;
		if(mat.find()) {
			result = mat.group(1).trim();
			result = result.replaceAll("\\s+"," ");
			return (result);
		} else {
			return (null);
		}
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setIndex(org.apache.lucene.document.Field.Index)
	 */
	@Override
	public void setIndex(Index aIndex) {
		index = aIndex;
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setStore(org.apache.lucene.document.Field.Store)
	 */
	@Override
	public void setStore(Store aStore) {
		store = aStore;

	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setTermVector(org.apache.lucene.document.Field.TermVector)
	 */
	@Override
	public void setTermVector(TermVector aTermVector) {
		termVector = aTermVector;
	}

}
