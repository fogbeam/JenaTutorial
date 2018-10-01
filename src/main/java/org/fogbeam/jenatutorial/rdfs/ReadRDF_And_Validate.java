package org.fogbeam.jenatutorial.rdfs;

import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;

public class ReadRDF_And_Validate
{

	public static void main( String[] args )
	{

		Model schema = FileManager.get().loadModel("file:data/input/turtle/ex1-schema.ttl");
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex1-data.ttl");
		
		InfModel infmodel = ModelFactory.createRDFSModel(schema, data);
		        
		
		ValidityReport validity = infmodel.validate();
		
		if (validity.isValid()) {
		    System.out.println("\nOK");
		} 
		else 
		{
		    System.out.println("\nConflicts");
		    for (Iterator i = validity.getReports(); i.hasNext(); ) {
		        ValidityReport.Report report = (ValidityReport.Report)i.next();
		        System.out.println(" - " + report);
		    }
		}
		
		System.out.println( "done" );
		
	}
	
	public static void printStatements(Model m, Resource s, Property p, Resource o)
	{
		PrintWriter writer=new PrintWriter(System.out);
		int index=0;
		for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); )
		{
			index++;
			Statement stmt = i.nextStatement();
			writer.println(" - " + PrintUtil.print(stmt));
		}
			
		writer.println(index);
		writer.flush();

	}	
	
}







/* 
StmtIterator iter = infmodel.listStatements();
while (iter.hasNext())
{
	Statement stmt      = iter.nextStatement();
	System.out.println( PrintUtil.print(stmt));
}		
*/


// Resource colin = infmodel.getResource("http://www.hpl.hp.com/semweb/2003/eg#colin");
// System.out.println("colin has types:");
// printStatements(infmodel, colin, RDF.type, null);
        
// Resource Person = infmodel.getResource("http://www.hpl.hp.com/semweb/2003/eg#Person");
// System.out.println("\nPerson has types:");
// printStatements(infmodel, Person, RDF.type, null);