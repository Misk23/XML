package com.xml.publications.utils.Database;


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


}
