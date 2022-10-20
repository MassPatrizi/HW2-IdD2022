package iddHw2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Reader {
	Path path = Paths.get("target/idx");

	public void ricerca() throws IOException, ParseException{
		Directory directory = FSDirectory.open(path);
		IndexReader reader = DirectoryReader.open(directory);		
		IndexSearcher searcher = new IndexSearcher(reader);


		QueryParser parser = new MultiFieldQueryParser(new String[] {"nome", "contenuto"}, new WhitespaceAnalyzer());;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Inserisci i termini della query: ");		
		Query query = parser.parse(keyboard.nextLine());
		System.out.println("\n");
		keyboard.close();
		TopDocs hits = searcher.search(query, 5);
		for (int i = 0; i < hits.scoreDocs.length; i++) { 
			ScoreDoc scoreDoc = hits.scoreDocs[i]; 
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println("Doc "+scoreDoc.doc + ": "+ doc.get("nome") + " (" + scoreDoc.score +")");
			Explanation explanation = searcher.explain(query, scoreDoc.doc);
			System.out.println(explanation);
		}
		

	}
}
