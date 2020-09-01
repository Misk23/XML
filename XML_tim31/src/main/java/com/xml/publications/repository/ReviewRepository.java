package com.xml.publications.repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.xml.publications.model.Review.Review;
import com.xml.publications.model.User.User;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import com.xml.publications.utils.Transformer.PDFTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.xmlrpc.webserver.ServletWebServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import javax.xml.transform.dom.DOMSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ReviewRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private ScientificPublicationService scientificPublicationService;

    @Autowired
    private Validator validator;

    public String saveReview(Review review) throws Exception {

        if(!validator.validateReviewAgainstSchema(review))
            return "Given review is not valid (schema validation)";
        databaseService.saveReview(review);

        Workflow workflow = databaseService.getWorkflowById(review.getPublicationId());
        workflow.getReviewers().getReviewerUsername().remove(review.getReviewedBy());
        if (workflow.getReviewers().getReviewerUsername().isEmpty()) {
            workflow.setStatus("revising");
            scientificPublicationService.changePublicationStatus(review.getPublicationId(), "revising");

        }

        databaseService.saveWorkflow(workflow);
        return "Review saved successfully";
    }

    public User findByUsername(String username) {
        try {
            return databaseService.getUserById(username);
        } catch (Exception e) {
            System.out.println("Finding user EXCEPTION");
            return null;
        }
    }

    public String generateHtmlAndForReview(String reviewId){
        PDFTransformer pdfTransformer = new PDFTransformer();

        try {
            DOMSource domSource =  databaseService.getReviewAsDom(reviewId);

            File htmlFile = new File("src/main/java/review.html");


            pdfTransformer.generateReviewHTML(domSource,"src/main/resources/data/review.xsl");

            pdfTransformer.generateReviewPDF("src/main/resources/data/reviewsPdf/" + reviewId + ".pdf");

            return htmlFile.getAbsolutePath();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Something is wrong!";
    }

    public ByteArrayResource getPublicationWithReviews(String publicationId) throws Exception {
        List<Review> reviews = databaseService.getReviesFromSpecificPublication(publicationId);

        for(Review review: reviews){
            String retVal = generateHtmlAndForReview(review.getReviewId());
        }

        Path scientificPublicationPath = Paths.get("src/main/java/scientificPublication.pdf");
		byte[] pdf = Files.readAllBytes(scientificPublicationPath);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Document document = null;
        PdfCopy writer = null;



        PdfReader reader = new PdfReader(pdf);
        int numberOfPages = reader.getNumberOfPages();

        if (document == null) {
            document = new Document(reader.getPageSizeWithRotation(1));
            writer = new PdfCopy(document, outStream);
            document.open();
        }
        PdfImportedPage page;

        for (int i = 0; i < numberOfPages;) {
            ++i;
            page = writer.getImportedPage(reader, i);
            writer.addPage(page);
        }


        File folder = new File("src/main/resources/data/reviewsPdf/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                byte[] reviewPdf= Files.readAllBytes(file.toPath());

                reader = new PdfReader(reviewPdf);
                numberOfPages = reader.getNumberOfPages();

                for (int i = 0; i < numberOfPages;) {
                    ++i;
                    page = writer.getImportedPage(reader, i);
                    writer.addPage(page);
                }
            }
        }

        document.close();
        outStream.close();

        OutputStream out = new FileOutputStream("src/main/java/mergedPublicationAndReviews.pdf");
        out.write((outStream.toByteArray()));
        out.close();

        FileUtils.cleanDirectory(folder);

        return new ByteArrayResource(outStream.toByteArray());
    }
}
