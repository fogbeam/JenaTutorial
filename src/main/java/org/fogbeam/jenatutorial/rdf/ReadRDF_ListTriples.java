package org.fogbeam.jenatutorial.rdf;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class ReadRDF_ListTriples 
{


	public static void main(String[] args) 
	{
		Dataset dataset = DatasetFactory.createMem();
		RDFDataMgr.read(dataset, "file:/development/presentations/trijug_semantic/JenaTutorial/data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);
		
		Model model = dataset.getDefaultModel();
		
		StmtIterator sIter = model.listStatements();
		
		while( sIter.hasNext() )
		{
			Statement s = sIter.next();
			System.out.println( "s: " + s );
		}
	}
}