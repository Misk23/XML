package com.xml.publications.utils.Transformer;

import java.io.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;

/**
 * 
 * Primer demonstrira koriscenje Apache FOP programskog API-a za 
 * renderovanje PDF-a primenom XSL-FO transformacije na XML dokumentu.
 *
 */
public class XSLFOTransformer {
	
	private FopFactory fopFactory;
	
	private TransformerFactory transformerFactory;


	public XSLFOTransformer() throws SAXException, IOException {
		
		// Initialize FOP factory object
		//fopFactory = FopFactory.newInstance(new File("src/main/java/fop.xconf"));
		fopFactory = FopFactory.newInstance();
		// Setup the XSLT transformer factory
		transformerFactory = new TransformerFactoryImpl();
	}

	public ByteArrayOutputStream generatePDF(String source_str, String xslt_fo_TemplatePath) throws Exception {

		// Point to the XSL-FO file
		File xslFile = new File(xslt_fo_TemplatePath);

		// Create transformation source
		StreamSource transformSource = new StreamSource(xslFile);

		// Initialize the transformation subject
		StreamSource source = new StreamSource(new StringReader(source_str));

		// Initialize user agent needed for the transformation
		FOUserAgent userAgent = fopFactory.newFOUserAgent();

		// Create the output stream to store the results
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		// Initialize the XSL-FO transformer object
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

		// Construct FOP instance with desired output format
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

		// Resulting SAX events
		Result res = new SAXResult(fop.getDefaultHandler());

		// Start XSLT transformation and FOP processing
		xslFoTransformer.transform(source, res);

		return outStream;
	}

}
