package util;

import java.io.FileOutputStream;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.DatasetFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.PrintUtil;

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