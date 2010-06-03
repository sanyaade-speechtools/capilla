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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private IndexWriter _writer;
	
	/**
	 * Contiene el analizador que utilizará _writer;
	 */
	private IAnalyzer _analyzer;
	
	/**
	 * Contiene la ruta del directorio donde están los ficheros a indexar.
	 */
	private String _pathFiles;
	
	/**
	 * Contiene la ruta del directorio en el que se almacenará el índice.
	 */
	private String _pathIndex;
	
	private Vector<Field> _fields;
	
	//--------------------------------------------------------------------------
	
	
	//Métodos
	
	/**
	 * Constructor por defecto de la clase indexFiles
	 */
	public indexFiles() {
		_writer = null;
		_analyzer = null;
		_pathFiles = PATH_FILES;
		_pathIndex = PATH_INDEX;
		_fields = null;
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
	public indexFiles(IAnalyzer aAnalyzer, String aPathFiles, String aPathIndex)
		throws CorruptIndexException, LockObtainFailedException, IOException,
		NoDirectorioNoLeer, NoDirectorio {
		
		_analyzer = aAnalyzer;
		_pathFiles = aPathFiles;
		_pathIndex = aPathIndex;
		isDirectoryPathAndRead(aPathFiles);
		isDirectory(aPathIndex);
		
		File fIndex = new File(aPathIndex);
		_writer = new IndexWriter(FSDirectory.open(fIndex),(Analyzer)_analyzer,CREATE,
				IndexWriter.MaxFieldLength.LIMITED);
		
		_fields = null;
	}
	
	private void isDirectoryPathAndRead(String aPath) throws NoDirectorioNoLeer {
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
	
	public void setFields (Vector<Field> fields) {
		_fields = fields;
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
				Matcher match = null;
				Pattern patt = null;
				//Campos a leer. Hay q inventar algo para desacoplar esto.
				String categorie = null;
				String question = null;
				String answer = null;
				String answerIndex = null;
				String url = null;
				
				while((line = fileRead.readLine()) != null) {
					if(line.contains("<Categorie Text=")) {
						patt = Pattern.compile("<Categorie Text=\"(.+)\">");
						match = patt.matcher(line);
						if(match.find()) {
							categorie = match.group(1).trim();
							categorie = categorie.replaceAll("\\s+", " ");
						}
					} else if (line.contains("<Question Text=")) {
						patt = Pattern.compile("<Question Text=\"(.+)\"");
						match = patt.matcher(line);
						if(match.find()) {
							question = match.group(1).trim();
							question = question.replace("\\s+", " ");
						}
					} else if (line.contains("<AnswerIndex>")) {
						answerIndex = line.replace("<AnswerIndex>", "");
						while(!line.contains("</AnswerIndex>")) {
							line = fileRead.readLine();
							answerIndex += " " + line;
						}
						answerIndex += " " + line.replace("</AnswerIndex>", "");
						answerIndex = answerIndex.replaceAll("\\n+", " ");
						answerIndex = answerIndex.replaceAll("\\s+", " ");
					} else if(line.contains("<Answer>")) {
						answer = line.replace("<Anaswer>", "");
						while(!line.contains("</Answer>")) {
							line = fileRead.readLine();
							answer += " " + line;
						}
						answer += " " + line.replace("</Answer>","");
						answer = answer.replaceAll("\\n", " ");
						answer = answer.replaceAll("\\s", " ");
					} else if(line.contains("<Url>")) {
						url = line.replace("<Url","");
						while(!line.contains("</Url>")) {
							line = fileRead.readLine();
							url += " " + line;
						}
						url += " " + line.replace("</Url>","");
						url.trim();
					}
				}
				
				Document doc = new Document();
				
				
				doc.add(new Field("categorie", categorie,Field.Store.YES,));
			}
		}
	}
	
	public void indexDocs() {
		File file = new File(_pathFiles);
		runDirectory(file);
	}
	
	
	//--------------------------------------------------------------------------
}
