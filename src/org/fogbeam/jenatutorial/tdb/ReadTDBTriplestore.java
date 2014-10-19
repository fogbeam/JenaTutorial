package org.fogbeam.jenatutorial.tdb;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDBFactory;

public class ReadTDBTriplestore 
{

	public static void main(String[] args) 
	{
		Dataset dataset = TDBFactory.createDataset( "jenastore/triples" );
		
		Model model = dataset.getDefaultModel();
		
		StmtIterator sIter = model.listStatements();
		
		while( sIter.hasNext())
		{
			Statement s = sIter.next();
			System.out.println( "s: " + s.toString() );
		}
		
	}

}
