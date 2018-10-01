package org.fogbeam.jenatutorial.skos;

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

public class SKOSMain32
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex32-data.ttl");
		
		Model skosData = FileManager.get().loadModel("file:data/input/rdfxml/skos.rdf");
		data.add(skosData);
		
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?x ?z WHERE { ?x <http://www.w3.org/2004/02/skos/core#broader> ?z }";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?x ?z WHERE { ?x <http://www.w3.org/2004/02/skos/core#narrower> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);		
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?x ?z WHERE { ?x <http://www.w3.org/2004/02/skos/core#broaderTransitive> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);		
		
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?x ?z WHERE { ?x <http://www.w3.org/2004/02/skos/core#narrowerTransitive> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		
		System.out.println( "done" );		
	}
}