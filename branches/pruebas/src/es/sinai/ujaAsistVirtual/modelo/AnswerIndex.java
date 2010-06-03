/**
 * AnswerIndex.java
 * @author Eugenio Martínez Cámara
 * @date 03/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.Reader;

import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;

import es.sinai.ujaAsistVirtual.exceptions.DocumentField;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public class AnswerIndex extends DocumentField {
	
	private final String FIELD = "<AnswerIndex>";
	
	private final String FIELD2 = "</AnswerIndex>";

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
		String result = buffer.replace(FIELD, "");
		while (buffer)
		return null;
	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setIndex(org.apache.lucene.document.Field.Index)
	 */
	@Override
	public void setIndex(Index index) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setStore(org.apache.lucene.document.Field.Store)
	 */
	@Override
	public void setStore(Store store) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.sinai.ujaAsistVirtual.exceptions.DocumentField#setTermVector(org.apache.lucene.document.Field.TermVector)
	 */
	@Override
	public void setTermVector(TermVector termVector) {
		// TODO Auto-generated method stub

	}

}
