package org.fogbeam.jenatutorial.tdb;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.tdb.TDBFactory;

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
