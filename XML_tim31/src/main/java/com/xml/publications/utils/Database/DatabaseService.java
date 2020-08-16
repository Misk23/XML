package com.xml.publications.utils.Database;

import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.User.User;
import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseService {

    public static final String USER_MODEL_PATH ="com.xml.publications.model.User";
    public static final String USER_COLLECTION_PATH="/db/publications/user";
    public static final String USER_SCHEMA_PATH="data/XSD/User.xsd";


    public static final String SCIENTIFIC_PUBLICATION_MODEL_PATH ="com.xml.publications.model.ScientificPublication";
    public static final String SCIENTIFIC_PUBLICATION_COLLECTION_PATH="/db/publications/scientificPublications";


    public User getUserById(String userId) throws Exception{

        Connection connection = new Connection();
        XMLResource xmlResource;

        try{
            xmlResource = connection.getResourceById(USER_COLLECTION_PATH, userId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        Unmarshaller unmarshaller = getUnmarshaller(USER_MODEL_PATH);

        try{
            return (User) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));
        }catch (NullPointerException ne){
            return null;
        }
    }


    public void saveUser(User user) throws Exception {

        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(USER_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(user.getUsername(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(USER_MODEL_PATH, USER_SCHEMA_PATH);
            marshaller.marshal(user, outputStream);

            xmlResource.setContent(outputStream);
            collection.storeResource(xmlResource);

        }finally {
            if (collection != null){
                try{
                    collection.close();
                }catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
            if (xmlResource != null){
                try{
                    ((EXistResource) xmlResource).freeResources();
                }catch ( XMLDBException xe){
                    xe.printStackTrace();
                }
            }

        }

    }



    public List<ScientificPublication> basicSearchScientificPublication(String text) {
        ArrayList<ScientificPublication> scientificPublications = new ArrayList<ScientificPublication>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/ScientificPublication");
            ResourceSet result = xpathService.query("//scientificPublication[@status='reviewing'  or @status='accepted'][.//*[contains(text(), '" + text +"')]]");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
                    StringReader reader = new StringReader(((XMLResource) res).getContent().toString());
                    ScientificPublication scientificPublication = (ScientificPublication) unmarshaller.unmarshal(reader);
                    scientificPublications.add(scientificPublication);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return scientificPublications;
    }


    public Marshaller getMarshaller(String modelPath,String schemaPath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(modelPath);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaPath);
        return marshaller;
    }

    public Unmarshaller getUnmarshaller(String modelPath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(modelPath);
        return context.createUnmarshaller();
    }
}
