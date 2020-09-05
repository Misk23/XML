package com.xml.publications.utils.RDF;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.util.FileUtils;
import org.xml.sax.SAXException;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

/**
 * 
 * Primer demonstrira ekstrakciju RDFa metapodataka iz 
 * XML dokumenta primenom GRDDL (Gleaning Resource Descriptions 
 * from Dialects of Languages) transformacije.
 * 
 */
public class MetadataExtractor {

	private TransformerFactory transformerFactory;

	private static final String XSLT_FILE = "src/main/resources/data/xsl/grddl.xsl";

	private static final String SPARQL_NAMED_GRAPH_URI = "/example/sparql/metadata";

	public MetadataExtractor() throws SAXException, IOException {

		// Setup the XSLT transformer factory
		transformerFactory = new TransformerFactoryImpl();
	}

	/**
	 * Generates RDF/XML based on RDFa metadata from an XML containing
	 * input stream by applying GRDDL XSL transformation.
	 *
	 * @param in XML containing input stream
	 * @param out RDF/XML output stream
	 */
	public void extractMetadata(InputStream in, OutputStream out) throws FileNotFoundException, TransformerException {

		// Create transformation source
		StreamSource transformSource = new StreamSource(new File(XSLT_FILE));

		// Initialize GRDDL transformer object
		Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);

		// Set the indentation properties
		grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
		grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// Initialize transformation subject
		StreamSource source = new StreamSource(in);

		// Initialize result stream
		StreamResult result = new StreamResult(out);

		// Trigger the transformation
		grddlTransformer.transform(source, result);

	}

	public void sentQuery(AuthenticationUtilities.ConnectionPropertiesJenaFuseki conn, HashSet<String> ids, String q, boolean loadQueryFromFile) throws Exception{
		String sparqlFilePath = "src/main/resources/data/query.rq";
		System.out.println(q);

		System.out.println("[INFO] Loading SPARQL query from file \"" + sparqlFilePath + "\"");
		String sparqlQuery = String.format(FileUtil.readFile(sparqlFilePath, StandardCharsets.UTF_8),
				conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI);

		// Create a QueryExecution that will access a SPARQL service over HTTP

		if (loadQueryFromFile){
			q = sparqlQuery;
		}

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, q);

		// Query the SPARQL endpoint, iterate over the result set...
		System.out.println("[INFO] Showing the results for SPARQL query using the result handler.\n");
		ResultSet results = query.execSelect();

		String varName;
		RDFNode varValue;

		while (results.hasNext()) {

			// A single answer from a SELECT query
			QuerySolution querySolution = results.next();
			Iterator<String> variableBindings = querySolution.varNames();

			// Retrieve variable bindings
			while (variableBindings.hasNext()) {

				varName = variableBindings.next();
				varValue = querySolution.get(varName);

				System.out.println(varName + ": " + varValue);

				if (varValue.toString().contains("/")){
					String[] about = varValue.toString().split("/");
					String id = about[about.length - 1];
					ids.add(id);
				}


			}
			System.out.println();
		}

		// Issuing the same query once again...

		// Create a QueryExecution that will access a SPARQL service over HTTP
		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, q);

		// Query the collection, dump output response as XML
		System.out.println("[INFO] Showing the results for SPARQL query in native SPARQL XML format.\n");
		results = query.execSelect();

		//ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

	}

	public static void main(String[] args) throws Exception {

		AuthenticationUtilities.ConnectionPropertiesJenaFuseki conn = AuthenticationUtilities.loadPropertiesJenaFuseki();

		// Referencing XML file with RDF data in attributes
		String xmlFilePath = "src/main/resources/data/xml/publications.xml";

		String rdfFilePath = "src/main/resources/data/proba.rdf";

		// Automatic extraction of RDF triples from XML file
		MetadataExtractor metadataExtractor = new MetadataExtractor();

		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		metadataExtractor.extractMetadata(
				new FileInputStream(new File(xmlFilePath)),
				new FileOutputStream(new File(rdfFilePath)));


		// Loading a default model with extracted metadata
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);

		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);


		// Writing the named graph
		System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();



		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");

		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);


		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();

		ResultSetFormatter.out(System.out, results);

		query.close() ;

		System.out.println("[INFO] End.");





		/*HashSet<String> ids = new HashSet<>();
		String sparqlFilePath = "src/main/resources/data/query.rq";
		System.out.println("[INFO] Loading SPARQL query from file \"" + sparqlFilePath + "\"");
		String sparqlQuery = String.format(FileUtil.readFile(sparqlFilePath, StandardCharsets.UTF_8),
				conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI);
		metadataExtractor.sentQuery(conn, ids, sparqlQuery, true);*/
	}


}