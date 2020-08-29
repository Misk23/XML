package com.xml.publications.utils.Database;


import com.xml.publications.model.CoverLetter.CoverLetter;
import com.xml.publications.model.Review.Review;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.User.User;
import org.springframework.stereotype.Component;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Component
public class Validator {

    public static final String USER_MODEL_PATH ="com.xml.publications.model.User";
    public static final String USER_SCHEMA_PATH="src/main/resources/data/XSD/User.xsd";

    public static final String SCIENTIFIC_PUBLICATION_MODEL_PATH ="com.xml.publications.model.ScientificPublication";
    public static final String SCIENTIFIC_PUBLICATION_SCHEMA_PATH="src/main/resources/data/XSD/ScientificPublication.xsd";

    public static final String COVER_LETTER_MODEL_PATH = "com.xml.publications.model.CoverLetter";
    public static final String COVER_LETTER_SCHEMA_PATH="src/main/resources/data/XSD/CoverLetter.xsd";

    public static final String REVIEW_MODEL_PATH = "com.xml.publications.model.Review";
    public static final String REVIEW_SCHEMA_PATH="src/main/resources/data/XSD/Review.xsd";



    public boolean validateUserAgainstSchema(User user) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(USER_MODEL_PATH);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(USER_SCHEMA_PATH));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(user, new DefaultHandler());
            return true;
        } catch ( Exception e) {
            return false;
        }
    }

    public boolean validatePublicationAgainstSchema(ScientificPublication scientificPublication) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SCIENTIFIC_PUBLICATION_MODEL_PATH);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(SCIENTIFIC_PUBLICATION_SCHEMA_PATH));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(scientificPublication, new DefaultHandler());
            return true;
        } catch ( Exception e) {
            return false;
        }
    }

    public boolean validateCoverLetterAgainstSchema(CoverLetter coverLetter){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(COVER_LETTER_MODEL_PATH);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(COVER_LETTER_SCHEMA_PATH));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(coverLetter, new DefaultHandler());
            return true;
        } catch ( Exception e) {
            return false;
        }
    }

    public boolean validateReviewAgainstSchema(Review review){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(REVIEW_MODEL_PATH);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(REVIEW_SCHEMA_PATH));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(review, new DefaultHandler());
            return true;
        } catch ( Exception e) {
            return false;
        }

    }


}
