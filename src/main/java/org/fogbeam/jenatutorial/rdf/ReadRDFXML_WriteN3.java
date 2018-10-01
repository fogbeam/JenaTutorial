package org.fogbeam.jenatutorial.rdf;

import java.io.FileOutputStream;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class ReadRDFXML_WriteN3 
{


	public static void main(String[] args) throws Exception
	{
		Dataset dataset = DatasetFactory.createMem();
		RDFDataMgr.read(dataset, "file:/development/presentations/trijug_semantic/JenaTutorial/data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);
		
		Model model = dataset.getDefaultModel();
		
		FileOutputStream fos = new FileOutputStream( "/development/presentations/trijug_semantic/JenaTutorial/data/output/n3/customers.rdf.n3" );
		RDFDataMgr.write( fos, model, Lang.N3 );
		fos.close();
		
	}
}