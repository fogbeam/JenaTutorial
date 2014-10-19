package org.fogbeam.jenatutorial.tdb;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.tdb.TDBFactory;

public class ReadRDF_WriteTDBTriplestore 
{

	public static void main(String[] args) 
	{
		Dataset dataset = TDBFactory.createDataset( "jenastore/triples" );
		
		dataset.begin(ReadWrite.WRITE);
		
		RDFDataMgr.read(dataset, "file:/development/presentations/trijug_semantic/JenaTutorial/data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);
		
		dataset.commit();
		dataset.end();
		dataset.close();
		
		
		
	}

}
