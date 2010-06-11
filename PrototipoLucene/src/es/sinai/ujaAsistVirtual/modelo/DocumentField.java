/**
 * DocumentField.java
 * @author Eugenio Martínez Cámara
 * @date 03/06/2010
 * @version
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.io.IOException;

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
	
	protected String name;
	
	protected String content;
		
	//--------------------------------------------------------------------------
	
	//métodos
	
	public Field.Store getStore() {
		return (store);
	}

	public void setStore(Field.Store aStore) {
		store = aStore;
	}

	public Field.Index getIndex() {
		return (index);
	}
	
	public void setIndex(Field.Index aIndex) {
		index = aIndex;
	}

	public Field.TermVector getTermVector() {
		return(termVector);
	}

	public void setTermVector(Field.TermVector aTermVector) {
		termVector = aTermVector;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	public abstract void parse(BufferedReader file, String buffer) throws IOException;
	
	public abstract boolean isThisField(String buffer);
	
	
	//--------------------------------------------------------------------------
}
