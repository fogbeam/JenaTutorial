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
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class BasicSparqlQueryV2
{

	public static void main( String[] args )
	{

		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex4-data.ttl");
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?x WHERE { ?x  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2000/01/rdf-schema#Class> }" ;


		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      Resource r = soln.getResource("x") ; // Get a result variable - must be a resource
		      
		      System.out.println( r.toString() );
		    }
		}
		finally
		{
			qexec.close();
		}
		
		System.out.println( "\n----------------------------------------------------\n" );
		queryString = "SELECT ?x ?y WHERE { <http://www.example.org/example#Kaneda> ?y  ?x}" ;


		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      Resource r = soln.getResource("x") ; // Get a result variable - must be a resource
		      Resource r2 = soln.getResource("y");
		      
		      System.out.println( r2.toString() + "\t\t" + r.toString() );
		    }
		}
		finally
		{
			qexec.close();
		}		
		
		System.out.println( "\n----------------------------------------------------\n" );
		queryString = "SELECT ?x ?y WHERE { <http://www.example.org/example#Smith> ?y  ?x}" ;


		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      Resource r = soln.getResource("x") ; // Get a result variable - must be a resource
		      Resource r2 = soln.getResource("y");
		      
		      System.out.println( r2.toString() + "\t\t" + r.toString() );
		    }
		}
		finally
		{
			qexec.close();
		}		
		
		
		System.out.println( "---------\ndone" );
	}
}
