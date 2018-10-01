package org.fogbeam.jenatutorial.tdb;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.tdb.TDBFactory;

public class ReadRDF_WriteTDBTriplestore 
{

	public static void main(String[] args) 
	{
		Dataset dataset = TDBFactory.createDataset( "/development/presentation/trijug_semantic/JenaTutorial/jenastore/triples" );
		
		dataset.begin(ReadWrite.WRITE);
		
		RDFDataMgr.read(dataset, "file:///development/presentation/trijug_semantic/JenaTutorial/data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);
		
		dataset.commit();
		dataset.end();
		dataset.close();
		
		
		
	}

}
