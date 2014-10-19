package org.fogbeam.jenatutorial.rdf;

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
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class ReadRDF_ListTriplesWithSelector
{

	public static void main( String[] args )
	{

		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex2-data.ttl");
		
		// InfModel infmodel = ModelFactory.createRDFSModel(data);
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		

		StmtIterator sIter = infmodel.listStatements();
		
		
		while( sIter.hasNext() )
		{
			Statement stmt = sIter.nextStatement();
			System.out.println( PrintUtil.print(stmt));
		}		
		
		
		System.out.println( "\n\n******************************\n\n" );

		Resource O = infmodel.createResource( "http://www.example.com/shop#Shirts" );
		
		/*
		Selector selector = new SimpleSelector((Resource)null, (Property)null, O);
		
		StmtIterator siter2 = infmodel.listStatements( selector );
		
		
		while( siter2.hasNext() )
		{
			Statement stmt = siter2.nextStatement();
			System.out.println( PrintUtil.print(stmt));
		}
		*/
		
		System.out.println( "\n\n******************************\n\n" );

		/*
		Property P = infmodel.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" );
		Selector selector2 = new SimpleSelector((Resource)null, (Property)P, O);
		
		StmtIterator siter3 = infmodel.listStatements( selector2 );
		
		
		while( siter3.hasNext() )
		{
			Statement stmt = siter3.nextStatement();
			System.out.println( PrintUtil.print(stmt));
		}		
		*/
		
		System.out.println( "done" );
		
	}
}
