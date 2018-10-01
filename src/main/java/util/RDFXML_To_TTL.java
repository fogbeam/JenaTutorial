package util;

import java.io.FileOutputStream;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.PrintUtil;

public class RDFXML_To_TTL 
{

	public static void main(String[] args) throws Exception
	{
		
		for( int i = 34; i <= 34; i++ )
		{
		
			Dataset dataset = DatasetFactory.createMem();
			RDFDataMgr.read(dataset, "file:data/jenarules/ex" + i + "/ex" + i + "-data.rdf", Lang.RDFXML);
		
			Model m = dataset.getDefaultModel();
		
			StmtIterator sIter = m.listStatements();
			while( sIter.hasNext())
			{
				Statement s = sIter.nextStatement();
				System.out.println( PrintUtil.print(s) );
			}
		
			FileOutputStream out = new FileOutputStream("data/input/turtle/ex" + i + "-data.ttl");
			RDFDataMgr.write(out, m, Lang.TURTLE);
		
			out.close();
		}
		
		System.out.println( "done" );

	}
}