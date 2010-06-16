/**
 * @file SearcherDocs.java
 * @author geni
 * @date 15/06/2010
 */
package es.sinai.ujaAsistVirtual.modelo;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * SearcherDocs
 * @author geni
 * @version 1.0
 * @date 15/06/2010
 */
public class SearcherDocs {
	
	private String pathIndex;
	
	private TopDocs result;
	
	private IndexSearcher searcher;
	
	private Analyzer analyzer;
	
	private Explanation[] explicationResults;
	
	public SearcherDocs(String aPathIndex, Analyzer aAnalyzer) {
		pathIndex = new String(aPathIndex);
		analyzer = aAnalyzer;
		explicationResults = null;
	}
	
	private void createExplanation(Query query, IndexSearcher searcher) throws IOException {
		explicationResults = new Explanation[result.totalHits];
		for(int i = 0; i < result.totalHits; i++) {
			ScoreDoc match = result.scoreDocs[i];
			explicationResults[i] = searcher.explain(query, match.doc);
		}
	}
	
	public void search(String userQuery) throws Exception {
		
		String defaultField = ConfigurationFile.getPropetiesValue(PropertiesName.SEARCH_FIELD);
		QueryParser parser = new QueryParser(Version.LUCENE_30, defaultField, analyzer);
		Query query = parser.parse(userQuery);
		
		FSDirectory directory = FSDirectory.open(new File(pathIndex));
		searcher = new IndexSearcher(directory);
		int nResults = Integer.parseInt(ConfigurationFile.getPropetiesValue(PropertiesName.SEARCH_N_RESULTS));
		result = searcher.search(query, nResults);
		createExplanation(query,searcher);
	}
	
	public void close() throws IOException{searcher.close();}
	
	public Document getDocument(int id) throws CorruptIndexException, IOException{
		return(searcher.doc(id));
	}
	
	public TopDocs getResult() {
		return(result);
	}
	
	public Explanation[] getExplicationResults() {
		return (explicationResults);
	}
}
