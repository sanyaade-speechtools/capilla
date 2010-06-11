/**
 * indexFiles.java
 * @author Eugenio Martínez Cámara
 * @date 31/05/2010
 * @version 0.1
 * 
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import es.sinai.ujaAsistVirtual.exceptions.NoDirectorio;
import es.sinai.ujaAsistVirtual.exceptions.NoDirectorioNoLeer;

/**
 * Clase que indexa ficheros, utilizando la librería de Lucene. 
 * @author Eugenio Martínez Cámara
 * En un principio será específica para utilizar el snowballAnalyzer, pero
 * se tiene que conseguir que por parámetros se le pueda pasar el analizador,
 * cogido de un fichero de parámetros. 
 */
public class indexFiles {
	
	//Constantes
	
	/**
	 * Ruta por defecto del directorio de índice.
	 */
	private final String PATH_FILES = "./";
	
	/**
	 * Ruta por defecto del fichero en el que se guardará el índice. 
	 */
	private final String PATH_INDEX = "./index_files";
	
	/**
	 * 
	 */
	private final boolean CREATE = true;
	
	//--------------------------------------------------------------------------
	
	//Atributos
	
	/**
	 * Contiene el indexador de los ficheros.
	 */
	private IndexWriter writer;
	
	/**
	 * Contiene el analizador que utilizará writer;
	 */
	private Analyzer analyzer;
	
	/**
	 * Contiene la ruta del directorio donde están los ficheros a indexar.
	 */
	private String pathFiles;
	
	/**
	 * Contiene la ruta del directorio en el que se almacenará el índice.
	 */
	private String pathIndex;
	
	/**
	 * Contiene los campos de los que estarán compuetos los documentos.
	 */
	private Vector<DocumentField> fields;
	
	//--------------------------------------------------------------------------
	
	
	//Métodos
	
	/**
	 * Constructor por defecto de la clase indexFiles
	 */
	public indexFiles() {
		writer = null;
		analyzer = null;
		pathFiles = PATH_FILES;
		pathIndex = PATH_INDEX;
		fields = null;
	}
	
	/**
	 * Constructor
	 * @param aAnalyzer
	 * @param aPathFiles
	 * @param aPathIndex
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 * @throws NoDirectorioNoLeer 
	 * @throws NoDirectorio 
	 */
	public indexFiles(Analyzer aAnalyzer, String aPathFiles, String aPathIndex,
			Vector<DocumentField> aFields)
			throws CorruptIndexException, LockObtainFailedException, IOException,
			NoDirectorioNoLeer, NoDirectorio {
		
		analyzer = aAnalyzer;
		pathFiles = aPathFiles;
		pathIndex = aPathIndex;
		isDirectoryPathAndRead(aPathFiles);
		isDirectory(aPathIndex);
		
		File fIndex = new File(aPathIndex);
		writer = new IndexWriter(FSDirectory.open(fIndex),analyzer,CREATE,
				IndexWriter.MaxFieldLength.LIMITED);
		
		fields = aFields;
	}
	
	private void isDirectoryPathAndRead(String aPath)
		throws NoDirectorioNoLeer {
		
		File f = new File(aPath);
		
		if(!f.isDirectory() && !f.canRead()) {
			String mss = "El directorio no existe o no se puede leer, comprue" +
			"belo por favor.";
			throw new NoDirectorioNoLeer(mss);
		}
	}
	
	private void isDirectory(String aPath) throws NoDirectorio {
		File f = new File(aPath);
		
		if(!f.isDirectory()) {
			String mss = "La ruta no corresponde a la de un directorio, " +
					"compruebelo por favor";
			throw new NoDirectorio(mss);
		}
	}
	
	public void setFields (Vector<DocumentField> aFields) {
		fields = aFields;
	}
	
	private void runDirectory(File file) throws IOException {
		if(file.canRead()) {
			if(file.isDirectory()) {
				String[] listNames = file.list();
				if(listNames != null) {//SI HAY UN I/O ERROR POR AHORA NO SE CONTROLA
					for(int i = 0; i < listNames.length; i++) {
						File fich = new File(listNames[i]);
						runDirectory(fich);
					}
				}
			} else {
				System.out.println("Adding " + file + "..."); //ESTO SE DEBERÁ DE QUITAR PARA LA APLICACIÓN FINAL O HACERLO CON UN OBSERVADOR

				BufferedReader fileRead = new BufferedReader(new FileReader(file));
				String line = null;
				int nFields= fields.size();
				int i = 0;
				DocumentField field = null;
				boolean evaluatedField = false;
				
				while((line = fileRead.readLine()) != null) {
					while(( i < nFields ) && (!evaluatedField)) {
						field = fields.get(i);
						if(field.isThisField(line)) {
							field.parse(fileRead, line);
							evaluatedField = true;
						}
						i++;
					}
					evaluatedField = false;
					i = 0;
				}
				
				Document doc = new Document();
				i = 0;
				while( i < nFields ) {
					field = fields.get(i);
					doc.add(new Field(field.getName(), field.getContent(),
							field.getStore(), field.getIndex()));
					i++;
				}
				
				writer.addDocument(doc);
			}
		}
	}
	
	public void indexDocs() throws IOException {
		File file = new File(pathFiles);
		runDirectory(file);
	}
	
	
	//--------------------------------------------------------------------------
}
