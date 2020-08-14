package com.xml.publications.utils.Database;


import com.xml.publications.model.User.User;
import org.springframework.stereotype.Component;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Component
public class Validator {

    public static final String USER_SCHEMA_PATH="src/main/resources/data/schemas/User.xsd";



    public boolean validateUserAgainstSchema(User user) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(USER_SCHEMA_PATH);
            JAXBSource jaxbSource = new JAXBSource(jaxbContext, user);

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


}
