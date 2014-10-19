package org.fogbeam.jenatutorial.rdfs;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class ShowInferredTypesFromDomainAndRange
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex6-data.ttl");
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		

		String queryString = "SELECT ?z WHERE { <http://www.example.org/example#TheFirm> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?z  }" ;
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		    	QuerySolution soln = results.nextSolution() ;
		    	System.out.println( soln );
		    }
		}
		finally
		{
			qexec.close();
		}		
		
		
		System.out.println( "\n---------------\n" );
		
		queryString = "SELECT ?z WHERE { <http://www.example.org/example#PhillipRhodes> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?z  }" ;


		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		try
		{
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		    	QuerySolution soln = results.nextSolution();
		    	System.out.println( soln );
		    }
		}
		finally
		{
			qexec.close();
		}		

		
		System.out.println( "\n----------\ndone" );
		
		
	}
}
