package org.fogbeam.jenatutorial.sparql;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.DatasetFactory;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.sparql.util.QueryExecUtils;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class FederatedSparqlQuery 
{

	public static void main(String[] args) 
	{
		Dataset dataset = DatasetFactory.createMem();
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, dataset.getDefaultModel() );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = 
			"SELECT * WHERE { SERVICE <http://dbpedia.org/sparql> {  <http://dbpedia.org/resource/George_Harrison> ?p ?o . } } LIMIT 10";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;		
		
		QueryExecUtils.executeQuery(qexec);
		
		
		System.out.println( "done" );

	}

}
