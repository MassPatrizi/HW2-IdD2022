package iddHw2;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

public class hw2 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Indixer i = new Indixer();
		Reader r = new Reader();
		try {
			long startTime = System.currentTimeMillis();	//variabile per contare il tempo di indicizzazione
			i.indicizza();
			long endTime = System.currentTimeMillis();		//termina il conto del tempo
	        System.out.println("L'indicizzazione ha impiegato " + (endTime - startTime) + " millisecondi\n");
			r.ricerca();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
