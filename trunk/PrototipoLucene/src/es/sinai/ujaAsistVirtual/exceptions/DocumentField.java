/**
 * DocumentField.java
 * @author Eugenio Martínez Cámara
 * @date 03/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.exceptions;

import java.io.Reader;

import org.apache.lucene.document.Field;

/**
 * @author Eugenio Martínez Cámara
 *
 */
public abstract class DocumentField {

	
	//atributos
	
	protected Field.Store store;
	
	protected Field.Index index;
	
	protected Field.TermVector termVector;
		
	//--------------------------------------------------------------------------
	
	//métodos
	
	public abstract Field.Store getStore();

	public abstract void setStore(Field.Store store);

	public abstract Field.Index getIndex();
	
	public abstract void setIndex(Field.Index index);

	public abstract Field.TermVector getTermVector();

	public abstract void setTermVector(Field.TermVector termVector);
	
	public abstract String parse(Reader file, String buffer);
	
	public abstract boolean isThisField(String buffer);
	
	
	//--------------------------------------------------------------------------
}
