package org.fogbeam.jenatutorial.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolutionMap;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class ParameterizedSparqlQuery
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex6-data.ttl");
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		

		Resource theFirmNode = infmodel.createResource("http://www.example.org/example#TheFirm");
		
		String queryString = "SELECT ?s ?z WHERE { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?z  }" ;
		
		QuerySolutionMap initialBinding = new QuerySolutionMap();
		initialBinding.add("s", theFirmNode );
		
		Query query = QueryFactory.create(queryString) ;
		
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel, initialBinding) ;

		QueryExecUtils.executeQuery(qexec);
	
		
		System.out.println( "\n----------\ndone" );
		
		
	}
}
