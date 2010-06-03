package com.autentia.lucene.es;

 
 import java.io.File;   
 import java.io.IOException;   
 import java.io.Reader;   
 import java.util.HashSet;   
 import java.util.Set;   
    
 import org.apache.lucene.analysis.Analyzer;   
 import org.apache.lucene.analysis.LowerCaseFilter;   
 import org.apache.lucene.analysis.StopFilter;   
 import org.apache.lucene.analysis.TokenStream;   
 import org.apache.lucene.analysis.WordlistLoader;   
 import org.apache.lucene.analysis.standard.StandardFilter;   
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;
    
    
 /** Filters {@link StandardTokenizer} with {@link StandardFilter}, {@link  
  * LowerCaseFilter}, {@link StopFilter} and {@link SpanishStemFilter}. */   
    
 /**  
  * Analyzer for Spanish using the SNOWBALL stemmer. Supports an external list of stopwords  
  * (words that will not be indexed at all).  
  * A default set of stopwords is used unless an alternative list is specified, the  
  * exclusion list is empty by default.  
  *  
  * @author jose  
  */   
    
 public class SpanishAnalyzer extends Analyzer {   
        
     /** An array containing some common Spanish words that are usually not  
      * useful for searching. Imported from http://www.unine.ch/info/clef/.  
      */   

     public static final String[] SPANISH_STOP_WORDS = { "" };  
       
     /** 
      * Contains the stopwords used with the StopFilter. 
      */  
     private Set stopTable = new HashSet();  
       
     /** 
      * Contains words that should be indexed but not stemmed. 
      */  
     private Set exclTable = new HashSet();
     
     private Version _version = Version.LUCENE_30;
       
     /** 
      * Builds an analyzer with the default stop words. 
      */  
     public SpanishAnalyzer() {
         stopTable = StopFilter.makeStopSet(SPANISH_STOP_WORDS);  
     }
     
     public SpanishAnalyzer(Version ver) {
    	 _version = ver;
         stopTable = StopFilter.makeStopSet(SPANISH_STOP_WORDS);  
     }
     /** Builds an analyzer with the given stop words. */  
     public SpanishAnalyzer(Version ver, String[] stopWords) {
    	 _version = ver;
         stopTable = StopFilter.makeStopSet(stopWords);  
     }  
       
     /** 
      * Builds an analyzer with the given stop words from file. 
      * @throws IOException  
      */  
     public SpanishAnalyzer(Version ver, File stopWords) throws IOException {
    	 _version = ver;
         stopTable = new HashSet(WordlistLoader.getWordSet(stopWords));  
     }  
       
     /** Constructs a {@link StandardTokenizer} filtered by a {@link 
      * StandardFilter}, a {@link LowerCaseFilter}, a {@link StopFilter} 
      * and a {@link SpanishStemFilter}. */  
     public final TokenStream tokenStream(String fieldName, Reader reader) {  
         TokenStream result = new StandardTokenizer(_version, reader);  		 
		 result = new StandardFilter(result);  
         result = new LowerCaseFilter(result);  
         result = new StopFilter(true,result, stopTable);  
         result = new SpanishStemFilter(result);  
         return result;  
     }  
 } 
