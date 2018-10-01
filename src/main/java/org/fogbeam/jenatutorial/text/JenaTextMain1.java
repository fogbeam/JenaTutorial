package org.fogbeam.jenatutorial.text;


import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.text.EntityDefinition;
import org.apache.jena.query.text.TextDatasetFactory;
import org.apache.jena.query.text.TextQuery;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.vocabulary.RDFS;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class JenaTextMain1 
{

	public static void main(String[] args) 
	{
		
		TextQuery.init();
		
		Dataset dataset = DatasetFactory.createMem();
		
		EntityDefinition entDef = new EntityDefinition("uri", "text", RDFS.label.asNode()) ;
	
		// Lucene, in memory.
		Directory dir =  new RAMDirectory();
	
		// Join together into a dataset
		Dataset ds = TextDatasetFactory.createLucene(dataset, dir, entDef, null);
		
		
		Model m = ds.getDefaultModel();
	
		Resource rSubject = m.createResource( "http://ontology.fogbeam.com/example/TestResource1" );
		Resource rSubject2 = m.createResource( "http://ontology.fogbeam.com/example/TestResource2" );
		
		try
		{
		
			Statement s = m.createStatement(rSubject, RDFS.label, "This is a Test Resource" );
		
			m.add( s );
		
			Statement s2 = m.createStatement(rSubject2, RDFS.label, "Bratwurst Test" );
			
			m.add( s2 );
			
			String baseQueryString =
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
					"PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
					"PREFIX dcterm: <http://purl.org/dc/terms/> " +
					"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
					"PREFIX text: <http://jena.apache.org/text#>";
						
					
			/* Do a SPARQL query using Jena-Text here... */
			String queryString = baseQueryString + " SELECT * { ?s  text:query( 'Bratwurst') ; . ?s rdfs:label ?label . }";
				
				
			Query query = QueryFactory.create(queryString) ;
				
				
			QueryExecution qexec = QueryExecutionFactory.create(query, ds );
			QueryExecUtils.executeQuery(qexec);
			
			m.close();
			
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		System.out.println( "done" );
	}

}