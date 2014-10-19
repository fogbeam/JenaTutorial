package org.fogbeam.jenatutorial.rdf;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.DatasetFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

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