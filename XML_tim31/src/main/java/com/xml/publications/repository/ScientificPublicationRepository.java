package com.xml.publications.repository;


import com.sun.xml.internal.ws.streaming.XMLReaderException;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import com.xml.publications.utils.Transformer.PDFTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.util.List;

@Component
public class ScientificPublicationRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private Validator validator;

    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return databaseService.basicSearchScientificPublication(text);
    }

    public void savePublication(ScientificPublication scientificPublication) throws Exception {
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
            System.out.println(id);
            scientificPublication = databaseService.getPublicationById(id);
        } catch (Exception e){
            e.printStackTrace();
            return "Publication with given id not found";
        }
        System.out.println(status);
        scientificPublication.setStatus(status);
        if (!validator.validatePublicationAgainstSchema(scientificPublication)) {
            System.out.println("greska u validaciji");
            return "New publication not valid (schema validaton)";
        }
        try {
            savePublication(scientificPublication);
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




}
