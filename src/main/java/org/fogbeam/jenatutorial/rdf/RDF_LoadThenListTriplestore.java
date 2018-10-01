package org.fogbeam.jenatutorial.rdf;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class RDF_LoadThenListTriplestore 
{


	public static void main(String[] args) 
	{
		Dataset persistentDataset = TDBFactory.createDataset("jenastore/triples");
		
		persistentDataset.begin(ReadWrite.WRITE);

		// Get model inside the transaction
		Model tdbModel = persistentDataset.getDefaultModel();	
				
		/* list the current triples in our triplestore */
		StmtIterator sIter = tdbModel.listStatements();
		
		System.out.println( "Initial Triples:\n\n");
		
		while( sIter.hasNext() )
		{
			Statement s = sIter.next();
			System.out.println( "s: " + s.toString() );
		}
		
		
		Dataset tempDataset = DatasetFactory.createGeneral();
		RDFDataMgr.read( tempDataset, "file:data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);

		tdbModel.add( tempDataset.getDefaultModel() );
		
		persistentDataset.commit();
		
		// we close this here just to make the point that when we read it off disk again, we
		// get the new triples that were stored
		persistentDataset.close();		
		
		
		/* Now list the triples stored in our triplestore */
		
		persistentDataset = TDBFactory.createDataset("jenastore/triples");
		
		// open in read-only mode this time
		persistentDataset.begin(ReadWrite.READ);

		// Get model inside the transaction
		tdbModel = persistentDataset.getDefaultModel();	
				
		/* list the current triples in our triplestore */
		sIter = tdbModel.listStatements();
		
		System.out.println( "Updated Triples:\n\n");
		
		while( sIter.hasNext() )
		{
			Statement s = sIter.next();
			System.out.println( "s: " + s.toString() );
		}
	}
}