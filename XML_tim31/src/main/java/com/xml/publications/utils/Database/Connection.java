package com.xml.publications.utils.Database;

import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;

@Component
public class Connection {

    public Database connectToDatabase(AuthenticationUtilities.ConnectionProperties conn)
            throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        return database;
    }


    public XMLResource getResourceById(String collectionPath, String resourceId,
                                       AuthenticationUtilities.ConnectionProperties connectionProperties)
            throws ClassNotFoundException, IllegalAccessException, XMLDBException, InstantiationException {

        Class<?> cl = Class.forName(connectionProperties.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection collection = null;
        XMLResource xmlResource = null;

        try{
            collection = DatabaseManager.getCollection(connectionProperties.uri + collectionPath);
            collection.setProperty(OutputKeys.INDENT, "yes");
            xmlResource = (XMLResource) collection.getResource(resourceId);
            return xmlResource;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if(xmlResource != null){
                try{
                    ((EXistResource) xmlResource).freeResources();
                } catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
            if(collection != null){
                try{
                    collection.close();
                } catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
        }
    }

    public  Collection getOrCreateCollection(String collectionPath, int offset,
                                                   AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {

        Collection collection = DatabaseManager.getCollection(conn.uri + collectionPath, conn.user, conn.password);

        // create the collection if it does not exist
        if(collection == null) {

            if(collectionPath.startsWith("/")) {
                collectionPath = collectionPath.substring(1);
            }

            String[] pathSegments = collectionPath.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= offset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCollection = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCollection == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    collection = mgt.createCollection(pathSegments[offset]);

                    collection.close();
                    parentCol.close();

                } else {
                    startCollection.close();
                }
            }
            return getOrCreateCollection(collectionPath, ++offset, conn);
        } else {
            return collection;
        }
    }

}
