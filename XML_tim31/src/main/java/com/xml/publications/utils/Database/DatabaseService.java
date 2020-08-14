package com.xml.publications.utils.Database;

import com.xml.publications.model.User.User;
import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Component
public class DatabaseService {

    public static final String USER_MODEL_PATH ="com.xml.publications.model/User";
    public static final String USER_COLLECTION_PATH="/db/users";
    public static final String USER_SCHEMA_PATH="data/schemas/User.xsd";





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
