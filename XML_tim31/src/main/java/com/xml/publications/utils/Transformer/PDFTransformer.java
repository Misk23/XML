package com.xml.publications.utils.Transformer;

import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import org.springframework.core.io.InputStreamResource;


/**
 * 
 * Primer demonstrira koriscenje iText PDF programskog API-a za 
 * renderovanje PDF-a na osnovu XML dokumenta. Alternativa Apache FOP-u.
 *
 */
public class PDFTransformer {
	
	private static DocumentBuilderFactory documentFactory;
	
	private static TransformerFactory transformerFactory;


	public static final String HTML_FILE = "src/main/java/scientificPublication.html";


	static {

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();

	}

	/**
	 * Creates a PDF using iText Java API
	 * @param filePath
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void generatePDF(String filePath) throws IOException, DocumentException {

		// Step 1
		Document document = new Document();

		// Step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

		// Step 3
		document.open();

		// Step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FILE));

		// Step 5
		document.close();

	}

	public void generateReviewPDF(String filePath) throws IOException, DocumentException {

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

		document.open();

		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("src/main/java/review.html"));

		document.close();

	}

	public org.w3c.dom.Document buildDocument(String filePath) {

		org.w3c.dom.Document document = null;
		try {

			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath));

			if (document != null)
				System.out.println("[INFO] File parsed with no errors.");
			else
				System.out.println("[WARN] Document is null.");

		} catch (Exception e) {
			return null;

		}

		return document;
	}


	public InputStreamResource getPdf(DOMSource domSource) {
		StringWriter stringWriter = new StringWriter();

		StreamResult result = new StreamResult(stringWriter);
		StreamSource transformSource = new StreamSource(new File("src/main/resources/data/ScientificPublication.xsl"));

		try {
			Transformer transformer = transformerFactory.newTransformer(transformSource);

			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.transform(domSource, result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter writer;

		try {
			writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(stringWriter.toString().getBytes(StandardCharsets.UTF_8)));
		}catch (Exception e){
			e.printStackTrace();
		}
		document.close();

		return new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));
	}

	public void generateScientificPublicationHTML(DOMSource source, String xslPath) throws FileNotFoundException {

		try {

			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");


			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	public void generateReviewHTML(DOMSource source, String xslPath) throws FileNotFoundException {

		try {

			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");


			StreamResult result = new StreamResult(new FileOutputStream("src/main/java/review.html"));
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}



	public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

		try {

			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

			// Transform DOM to HTML
			DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException, DocumentException {

		System.out.println("[INFO] " + PDFTransformer.class.getSimpleName());

		// Creates parent directory if necessary
		File pdfFile = new File("src/main/java/review.pdf");

		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}

		PDFTransformer pdfTransformer = new PDFTransformer();

		pdfTransformer.generateHTML("src/main/resources/data/XSD/instance1.xml", "src/main/resources/data/review.xsl");
		pdfTransformer.generatePDF("src/main/java/review.pdf");

		System.out.println("[INFO] File \"" + "src/main/java/review.pdf" + "\" generated successfully.");
		System.out.println("[INFO] End.");

	}

}

