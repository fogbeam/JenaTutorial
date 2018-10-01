package org.fogbeam.jenatutorial.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class BasicSparqlQuery
{

	public static void main( String[] args )
	{

		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex2-data.ttl");
		
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?x WHERE { ?x  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.com/shop#Shirts>}" ;

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel);

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    while( results.hasNext() )
		    {
		    	QuerySolution soln = results.nextSolution();
		    	System.out.println( "soln: " + soln.toString());
		    }
		}
		finally
		{
			qexec.close();
		}
		
		System.out.println( "done" );
		
	}
}
