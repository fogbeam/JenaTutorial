package org.fogbeam.jenatutorial.owl;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class OWLMain31
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex31-data.ttl");
		
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?facility ?location WHERE { ?p <http://www.example.org/example#manufactureLocation> ?location . ?p <http://mfg.example.org/mfg#facility> ?facility . }";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		    	QuerySolution soln = results.nextSolution() ;
		    	
		    	RDFNode location = null;
		    	try 
		    	{
		    		location = (Resource)soln.getResource("location");
		    	}
		    	catch( ClassCastException e ) {}
		      
		    	if( location == null ) 
		    	{
		    		// maybe it's a literal
		    		// System.out.println( "it's a literal" );
		    		location = (Literal)soln.getLiteral( "location" );
		    	}
		    	
		    	
		    	RDFNode facility = null;
		    	try 
		    	{
		    		facility = (Resource)soln.getResource("facility");
		    	}
		    	catch( ClassCastException e ) {}
		      
		    	if( facility == null ) 
		    	{
		    		// maybe it's a literal
		    		// System.out.println( "it's a literal" );
		    		facility = (Literal)soln.getLiteral( "facility" );
		    	}	    	
		    	
		    	// String facility = "TBD";
		    	
		    	String res = location.toString() + "\t" + facility.toString();
		    	System.out.println( res );

		    }
		}
		finally
		{
			qexec.close();
		}	
		
		System.out.println( "\n-------DONE--------\n" );		
	}
}
