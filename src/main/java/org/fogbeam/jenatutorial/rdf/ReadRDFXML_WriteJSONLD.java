package org.fogbeam.jenatutorial.rdf;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class ReadRDFXML_WriteJSONLD 
{

	public static void main(String[] args) throws Exception
	{
		Dataset dataset = DatasetFactory.createGeneral();
		RDFDataMgr.read(dataset, "file:data/input/rdfxml/customers.rdf.xml", Lang.RDFXML);
		
		Model model = dataset.getDefaultModel();
		
		File outFile = new File( "data/output/jsonld/customers.rdf.jsonld"  );
		if( outFile.exists() ) 
		{
			outFile.delete();
		}
		
		FileOutputStream fos = new FileOutputStream( outFile );
		RDFDataMgr.write( fos, model, Lang.JSONLD );
		fos.close();
		
		System.out.println( "done" );
	}
}