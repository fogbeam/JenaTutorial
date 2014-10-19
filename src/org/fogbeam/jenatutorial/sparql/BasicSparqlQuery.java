package org.fogbeam.jenatutorial.sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.sparql.util.QueryUtils;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;
import com.hp.hpl.jena.vocabulary.VCARD;

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
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

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
