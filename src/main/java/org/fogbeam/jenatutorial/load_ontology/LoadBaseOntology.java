package org.fogbeam.jenatutorial.load_ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.tdb.TDBFactory;

public class LoadBaseOntology 
{

	public static void main(String[] args) 
	{

		Dataset dataset = TDBFactory.createDataset("jenastore/triples");
		
		dataset.begin(ReadWrite.WRITE);

		// Get model inside the transaction
		Model tdbModel = dataset.getDefaultModel();
		
		
		Property rdfsLabel = tdbModel.createProperty("http://www.w3.org/2000/01/rdf-schema#", "label");
		
		
		ResIterator resIter = tdbModel.listSubjectsWithProperty(rdfsLabel, "baseOntologyLoaded");
		
		if( resIter.hasNext() )
		{
			System.out.println( "our ontology is already loaded, do nothing..." );
		}
		else
		{
		
			System.out.println( "Our ontology is NOT loaded, loading now..." );
			
			OntModel onto = null;
			try
			{
				onto = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, tdbModel);
		
				// Load our Ontology from the OWL file on disk
				onto.read("file:ontology/sys_ontology.owl", "TURTLE");
				
				tdbModel.add(onto);
		
				dataset.commit();
		
			}
			finally
			{
				if( dataset != null )
				{
					dataset.end();
				}
			}
		}		
	}

}
