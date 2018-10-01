package org.fogbeam.jenatutorial.sparql;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class FederatedSparqlQuery_Wikidata 
{

	public static void main(String[] args) 
	{
		Dataset dataset = DatasetFactory.createGeneral();
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, dataset.getDefaultModel() );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString =   // tell me all about Germany (wd:Q183)
			"SELECT * WHERE { SERVICE <https://query.wikidata.org/sparql> { <http://www.wikidata.org/entity/Q183> ?p ?o . } } LIMIT 100";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;		
		
		QueryExecUtils.executeQuery(qexec);
		
		
		System.out.println( "done" );

	}

}
