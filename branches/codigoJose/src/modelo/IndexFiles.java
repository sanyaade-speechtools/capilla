package modelo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.autentia.lucene.es.SpanishAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

/** Index all text files under a directory. */
public class IndexFiles {
  
  private IndexFiles() {}
    
  // Definimos el analizador que se va a utilizar
  //public static	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
  public static Analyzer analyzer = new SpanishAnalyzer(Version.LUCENE_30);
  
  /** Index all text files under a directory.
   * @param 1 --> The directory path of the files to index.
   * @param 2 --> The directory path where the index will be created. It hasn't exist.
   * */  
  public static void main(String[] args) {
    String usage = "java IndexFiles <data-dir> <index-dir>";
    if (args.length != 2) {
      System.err.println("Usage: " + usage);
      System.exit(1);
    }
	
	// INDEX_DIR es el directorio de salida, donde se crea el índice
	final File INDEX_DIR = new File(args[1]);
    if (INDEX_DIR.exists()) {
      System.out.println("Cannot save index to '" +INDEX_DIR+ "' directory, please delete it first");
      System.exit(1);
    }
    
	// docDir es el directorio donde se encuentra los ficheros a indexar
    final File docDir = new File(args[0]);
    if (!docDir.exists() || !docDir.canRead()) {
      System.out.println("Document directory '" +docDir.getAbsolutePath()+ "' does not exist or is not readable, please check the path");
      System.exit(1);
    }
    
    Date start = new Date();
    try {
      IndexWriter writer = new IndexWriter(FSDirectory.open(INDEX_DIR), analyzer, true, IndexWriter.MaxFieldLength.LIMITED);	  
      System.out.println("Indexing to directory '" +INDEX_DIR+ "'...");
      indexDocs(writer, docDir);
      System.out.println("Optimizing...");
      writer.optimize();
      writer.close();

      Date end = new Date();
      System.out.println(end.getTime() - start.getTime() + " total milliseconds");

    } catch (IOException e) {
      System.out.println(" caught a " + e.getClass() +
       "\n with message: " + e.getMessage());
    }
  }

  /* -------------------------------------------------------------------------------
   * 
   * indexDocs: función que recorre un directorio e indexa los ficheros que se encuentra
   * 
   * ------------------------------------------------------------------------------- */
  static void indexDocs(IndexWriter writer, File file) throws IOException {
    // do not try to index files that cannot be read
    
	if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        // an IO error could occur
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
            indexDocs(writer, new File(file, files[i]));
          }
        }
      } else {
		System.out.print("adding " + file + " ... ");
		try {
			BufferedReader fichTextos = new BufferedReader(new FileReader(file));
			String lineTexts = new String();			
			Matcher mat=null;
			Pattern pat=null;
			// Campos a leer
			String categoria = new String();
			String pregunta = new String();
			String respindex = new String();
			String respfull = new String();
			String link = new String();
			
			// leemos las lineas del fichero
			while (true){
				lineTexts = fichTextos.readLine();
				if (lineTexts == null) break;
				
				if (lineTexts.contains("<Categorie Text=")){
					pat=Pattern.compile("<Categorie Text=\"(.+)\">");
					mat=pat.matcher(lineTexts);
					if (mat.find()){ 
						categoria = new String(mat.group(1).trim());
						categoria = categoria.replaceAll("\\s+"," ");//Se eliminan espacios blancos ([ \t\n\x0B\f\r])
						//System.out.println("\tCategoria --> '"+categoria+"'");
					}
				}
				if (lineTexts.contains("<Question Text=")){
					pat=Pattern.compile("<Question Text=\"(.+)\">");
					mat=pat.matcher(lineTexts);
					if (mat.find()){ 
						pregunta = new String(mat.group(1).trim());
						pregunta = pregunta.replaceAll("\\s+"," ");
						//System.out.println("\tPregunta --> '"+pregunta+"'");
					}
				}
				if (lineTexts.contains("<AnswerIndex>")){					
					// Le quitamos la cadena <AnswerIndex>
					respindex = lineTexts.replace("<AnswerIndex>","");
					while (!lineTexts.contains("</AnswerIndex>")){
						lineTexts = fichTextos.readLine();
						respindex += " "+lineTexts;
					}
					respindex = respindex.replace("</AnswerIndex>","");	
					respindex = respindex.replaceAll("\\n+"," ");				
					respindex = respindex.replaceAll("\\s+"," ");					
					respindex = respindex.trim();
					//System.out.println("\tAnswerIndex --> '"+respindex+"'");
				}
				if (lineTexts.contains("<Answer>")){					
					// Le quitamos la cadena <Answer>
					respfull = lineTexts.replace("<Answer>","");
					while (!lineTexts.contains("</Answer>")){
						lineTexts = fichTextos.readLine();
						respfull += " "+lineTexts;
					}
					respfull = respfull.replace("</Answer>","");	
					respfull = respfull.replaceAll("\\n+"," ");					
					respfull = respfull.replaceAll("\\s+"," ");	
					respfull = respfull.trim();				
					//System.out.println("\tAnswer --> '"+respfull+"'");
				}
				if (lineTexts.contains("<Url>")){					
					// Le quitamos la cadena <Url>
					link = lineTexts.replace("<Url>","");
					while (!lineTexts.contains("</Url>")){
						lineTexts = fichTextos.readLine();
						link += " "+lineTexts;
					}
					link = link.replace("</Url>","");
					link = link.trim();
					//System.out.println("\tUrl --> '"+link+"'");
				}
	
				// Creamos el objeto Document con los campos necesarios
				// IMPORTANTE: siempre hay que utilizar un analizador con LUCENE aunque el texto original esté preprocesado porque si no Lucene considera todo el texto
				// de un documento como un solo termino. Por tanto, si ya tenemos la colección preprocesada usaremos el Analizador WhitespaceAnalyzer
				/* 
				 *  Campos Field.Index:
				 *  - NOT_ANALYZED: el campo es indexado pero no se utiliza para buscar. Todo se trata como un unico token
				 *  - ANALYZED: el campo se indexa y además se utiliza para buscar. Se aplica un analizador (divide el texto en tokens)
				 *  - NO: ni se indexa ni se hace disponible para buscarr
				 * 
				 *  Campos Field.Store:
				 *  - YES: se almacena el campo, es decir, se guarda su valor original (es un campo que luego va a ser mostrado al usuario en los resultados) 
				 * 			ejemplos de campos "YES": nombre, filepath, urls, autores, numeros, fechas
				 *  - NO: no se almacena el campo (no se guarda su forma original). Normalmente se utiliza con campos "ANALYZED" --> se transforman en tokens
				 * 
				 */
				Document indexdoc  = new Document();	
				indexdoc.add(new Field("category", categoria, Field.Store.YES, Field.Index.NOT_ANALYZED));
				indexdoc.add(new Field("question", pregunta, Field.Store.NO, Field.Index.ANALYZED)); // va a ser el campo de búsqueda
				indexdoc.add(new Field("answerindex", respindex, Field.Store.YES, Field.Index.NOT_ANALYZED));
				indexdoc.add(new Field("answerfull", respfull, Field.Store.YES, Field.Index.NOT_ANALYZED));
				indexdoc.add(new Field("url", link, Field.Store.YES, Field.Index.NOT_ANALYZED));
					
				try{
					writer.addDocument(indexdoc);					
				}catch (java.lang.NullPointerException np){
					System.out.println("Excepcion null al añadir el documento: "+np);
				}catch (Exception ep){
					System.out.println("Excepcion al añadir el documento: "+ep);
				}
				
			} // fin del while true
					
		} // fin del try
		catch (FileNotFoundException fnfe) {
			;
		}  		
		System.out.println("OK");
      } // fin del else (es un fichero)
    } // fin del if
  } // fin del indexDocs
  
}
