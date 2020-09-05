package com.xml.publications.repository;


import com.xml.publications.DTO.MessageDTO;
import com.xml.publications.DTO.PublicationSearchDTO;
import com.xml.publications.DTO.ScientificPublicationEditDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.Workflow.ObjectFactory;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.service.NotificationService;
import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import com.xml.publications.utils.RDF.MetadataExtractor;
import com.xml.publications.utils.Transformer.PDFTransformer;
import org.apache.xmlrpc.webserver.ServletWebServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ScientificPublicationRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private Validator validator;

    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return databaseService.basicSearchScientificPublication(text);
    }

    public void savePublication(ScientificPublication scientificPublication) throws Exception {
        Workflow workflow = (new ObjectFactory()).createWorkflow();
        workflow.setPublicationId(scientificPublication.getPublicationId());
        workflow.setAuthors((new ObjectFactory()).createWorkflowAuthors());
        workflow.getAuthors().setAuthorUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername());

        workflow.setReviewers((new ObjectFactory()).createWorkflowReviewers());
        workflow.setStatus(scientificPublication.getStatus());
        databaseService.saveWorkflow(workflow);
        databaseService.savePublication(scientificPublication);

        //TODO RDF metoda u ovu liniju koda
    }

    public String savePublicationFromFile(File xmlFile){

        ScientificPublication scientificPublication = databaseService.getPublicationFromXMLFile(xmlFile);

        if (scientificPublication == null)
            return "Publication class from xml file failure";

        if ( !validator.validatePublicationAgainstSchema(scientificPublication))
            return "Given publication is not valid (schema validation)";

        try{
            savePublication(scientificPublication);
            //TODO NOTIFIKCIJA
            notificationService.notificationPublicationSubmitted(scientificPublication);

        }catch (Exception e){
            e.printStackTrace();
            return "Saving publication unsuccessful";
        }

        return "Saving publication successful";
    }

    public String savePublicationFromText(String xmlFile){

        ScientificPublication scientificPublication = databaseService.getPublicationFromXMLString(xmlFile);

        if (scientificPublication == null)
            return "Publication class from xml string failure";

        if ( !validator.validatePublicationAgainstSchema(scientificPublication))
            return "Given publication is not valid (schema validation)";
        try{
            savePublication(scientificPublication);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving publication unsuccessful";
        }

        return "Saving publication successful";
    }

    public List<ScientificPublication> getMyPublications(String username){
        System.out.println(username);
        return databaseService.getPublicationsByUsername(username);
    }

    public String changeStatus(String id, String status){
        ScientificPublication scientificPublication;
        try{
            scientificPublication = databaseService.getPublicationById(id);
        } catch (Exception e){
            e.printStackTrace();
            return "Publication with given id not found";
        }
        scientificPublication.setStatus(status);
        if (!validator.validatePublicationAgainstSchema(scientificPublication)) {
            System.out.println("greska u validaciji");
            return "New publication not valid (schema validaton)";
        }
        try {
            databaseService.savePublication(scientificPublication);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving publication unsuccessful";
        }
        return "Saving publication successful";
    }

    public String showPublication(String publicationId){
        PDFTransformer pdfTransformer = new PDFTransformer();

        try {
            DOMSource domSource =  databaseService.getPublicationAsDom(publicationId);

            File htmlFile = new File("src/main/java/scientificPublication.html");


            pdfTransformer.generateScientificPublicationHTML(domSource,"src/main/resources/data/ScientificPublication.xsl");

            pdfTransformer.generatePDF("src/main/java/scientificPublication.pdf");

            return htmlFile.getAbsolutePath();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Something is wrong!";
    }

    public List<ScientificPublication> getAllAcceptedPublications(){
        return databaseService.getAllAcceptedPublications();
    }


    public MessageDTO editPublication(ScientificPublicationEditDTO sp) throws Exception{
        return databaseService.editPublication(sp);
    }

    public List<ScientificPublication> advancedSearch(PublicationSearchDTO ps) throws Exception {
        List<ScientificPublication> scientificPublications = new ArrayList<>();
        HashSet<String> ids = new HashSet<String>();
        StringBuilder filter = new StringBuilder();

        MetadataExtractor metadataExtractor = new MetadataExtractor();
        String query = "SELECT * FROM <http://localhost:3030/Publication/data/example/sparql/metadata> WHERE { ";

        if (!ps.getTitle().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/title> ?title .\n";
            filter.append("FILTER regex(str(?title), \"" + ps.getTitle().trim() + "\") \n");
        }

        if (!ps.getAuthor().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/authorUsername> ?authorUsername .\n";
            filter.append("FILTER regex(str(?authorUsername), \"" + ps.getAuthor().trim() + "\") \n");
        }

        if (!ps.getKeyword().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/keywords> ?keywords .\n";
            filter.append("FILTER regex(str(?keywords), \"" + ps.getKeyword().trim() + "\") \n");
        }

        if (!ps.getText().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/text> ?text .\n";
            filter.append("FILTER regex(str(?text), \"" + ps.getText().trim() + "\") \n");
        }

        if (!ps.getChapterTitle().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/chapterTitle> ?chapterTitle .\n";
            filter.append("FILTER regex(str(?chapterTitle), \"" + ps.getChapterTitle().trim() + "\") \n");
        }

        if (!ps.getAuthorCitation().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/authorOfCitation> ?authorOfCitation .\n";
            filter.append("FILTER regex(str(?authorOfCitation), \"" + ps.getAuthorCitation().trim() + "\") \n");
        }

        if (!ps.getPublicationTitle().trim().equalsIgnoreCase("")){
            query += "?publication <http://www.ftn.uns.ac.rs/rdf/publication/predicate/publicationTitle> ?publicationTitle .\n";
            filter.append("FILTER regex(str(?publicationTitle), \"" + ps.getPublicationTitle().trim() + "\") \n");
        }

        boolean loadQueryFromFile = true;
        if (filter.toString().contains("regex")) {
            query += filter.toString();
            loadQueryFromFile = false;
        }

        query += "}";

        metadataExtractor.sentQuery(AuthenticationUtilities.loadPropertiesJenaFuseki(), ids, query, loadQueryFromFile);

        for(String id: ids){
            ScientificPublication scientificPublication = databaseService.getPublicationById(id);
            scientificPublications.add(scientificPublication);
        }

        return scientificPublications;
    }


}
