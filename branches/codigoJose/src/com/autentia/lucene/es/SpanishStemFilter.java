package com.autentia.lucene.es; 
 
import java.io.IOException; 
 
import org.tartarus.snowball.ext.SpanishStemmer;
import org.apache.lucene.analysis.TokenFilter; 
import org.apache.lucene.analysis.TokenStream; 
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
 
/**  
 * Spanish stemming algorithm. 
 */ 
public final class SpanishStemFilter extends TokenFilter { 
     
    private SpanishStemmer stemmer; 
     
     
    public SpanishStemFilter(TokenStream in) { 
    	super(in); 
        stemmer = new SpanishStemmer(); 
    } 
     
    /** Returns the next input Token, after being stemmed */ 
    /*public final TermAttribute next() throws IOException { 
    	
        if ((token = input.next()) == null) { 
            return null; 
        } 
        else {
            stemmer.setCurrent(token.termText()); 
            stemmer.stem(); 
            String s = stemmer.getCurrent(); 
            if ( !s.equals( token.termText() ) ) { 
                return new Token( s, token.startOffset(), 
                token.endOffset(), token.type() ); 
            } 
            return token; 
        }
         
    } */
     
    /** 
     * Set a alternative/custom Stemmer for this filter. 
     */ 
    public void setStemmer(SpanishStemmer stemmer) { 
        if ( stemmer != null ) { 
            this.stemmer = stemmer; 
        } 
    }

	@Override
	public boolean incrementToken() throws IOException {
		if(input.incrementToken()) {
			TermAttribute tokenText = (TermAttribute)input.getAttribute(TermAttribute.class);
			//PRUEBA
			OffsetAttribute tokenOffset = (OffsetAttribute)input.getAttribute(OffsetAttribute.class);
			System.out.println("\nToken antes del stemmer: " + tokenText.term() + "; Start offset: " + tokenOffset.startOffset() +
					"; End offset: " + tokenOffset.endOffset() + ".");
			stemmer.setCurrent(tokenText.term());
			stemmer.stem();
			String tokenStem = stemmer.getCurrent();
			System.out.println("Token despues del stemmer: " + tokenStem + "; Start offset: " + tokenOffset.startOffset() +
					"; End offset: " + tokenOffset.endOffset() + ".");
			if(!tokenStem.endsWith(tokenText.term())) {
				tokenText.setTermBuffer(tokenStem);
				System.out.println("\nToken ASIGNADO después stemmer: " + tokenText.term() + "; Start offset: " + tokenOffset.startOffset() +
						"; End offset: " + tokenOffset.endOffset() + ".");
			}
			System.out.println("---------------------------------------------------------------------");
			return true;
		} else {
			return false;
		}
	} 
}
