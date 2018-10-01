package org.fogbeam.jenatutorial.skos;

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
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class SKOSMain33
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex33-data.ttl");
		
		Model skosData = FileManager.get().loadModel("file:data/input/rdfxml/skos.rdf");
		data.add(skosData);
		
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?x ?y ?z WHERE { ?x ?y ?z }" ;


		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
			

		if( false )
		{
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?x ?z WHERE { ?x <http://www.w3.org/2004/02/skos/core#broader> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		    	QuerySolution soln = results.nextSolution() ;
		    	
		    	RDFNode x = (Resource)soln.getResource("x");
		    	
		    	String y = "<http://www.w3.org/2004/02/skos/core#broader>";
		    	
		    	RDFNode z = null;
		    	try 
		    	{
		    		z = (Resource)soln.getResource("z");
		    	}
		    	catch( ClassCastException e ) {}
		      
		    	if( z == null ) 
		    	{
		    		// maybe it's a literal
		    		// System.out.println( "is literal" );
		    		z = (Literal)soln.getLiteral( "z" );
		    	}
		    	
		    	String res = x.toString() + "\t" + y.toString() + "\t" + z.toString();
		    	
		    	System.out.println( res );
		    	
		    }
		}
		finally
		{
			qexec.close();
		}				
		
		}
		
		
		System.out.println( "done" );		
	}
}