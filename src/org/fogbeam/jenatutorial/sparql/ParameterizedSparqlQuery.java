package org.fogbeam.jenatutorial.sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.sparql.util.QueryExecUtils;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

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
