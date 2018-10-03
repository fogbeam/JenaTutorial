package org.fogbeam.jenatutorial.owl;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.util.FileManager;

public class OWL_SameRecipe
{
	/* NOTE: the levels of inference in this example are a little tricky.  Consider skipping in the
	 * presentation in the name of expediency
	 */
	
	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex25-data.ttl");
		
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?z WHERE { <http://www.example.org/example#GraduallyMix> <http://www.example.org/example#neighborStep> ?z }";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		

		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?z WHERE { <http://www.example.org/example#GraduallyMix> <http://www.example.org/example#inSameRecipe> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		System.out.println( "done" );		
	}
}