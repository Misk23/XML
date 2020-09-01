package com.xml.publications.utils.Database;

import com.xml.publications.DTO.*;
import com.xml.publications.model.CoverLetter.CoverLetter;
import com.xml.publications.model.Notification.Notification;
import com.xml.publications.model.Review.Review;
import com.xml.publications.model.ScientificPublication.ObjectFactory;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.User.User;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.utils.Authentication.AuthenticationUtilities;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.*;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseService {

    public static final String USER_MODEL_PATH ="com.xml.publications.model.User";
    public static final String USER_COLLECTION_PATH="/db/publications/user";
    public static final String USER_SCHEMA_PATH="data/XSD/User.xsd";

    public static final String SCIENTIFIC_PUBLICATION_MODEL_PATH ="com.xml.publications.model.ScientificPublication";
    public static final String SCIENTIFIC_PUBLICATION_COLLECTION_PATH="/db/publications/scientificPublications";
    public static final String SCIENTIFIC_PUBLICATION_SCHEMA_PATH="data/XSD/ScientificPublication.xsd";

    public static final String COVER_LETTER_MODEL_PATH = "com.xml.publications.model.CoverLetter";
    public static final String COVER_LETTER_COLLECTION_PATH = "/db/publications/coverLetters";
    public static final String COVER_LETTER_SCHEMA_PATH="data/XSD/CoverLetter.xsd";

    public static final String WORKFLOW_MODEL_PATH = "com.xml.publications.model.Workflow";
    public static final String WORKFLOW_COLLECTION_PATH = "/db/publications/workflow";
    public static final String WORKFLOW_SCHEMA_PATH="data/XSD/Workflow.xsd";

    public static final String REVIEW_MODEL_PATH = "com.xml.publications.model.Review";
    public static final String REVIEW_COLLECTION_PATH = "/db/publications/review";
    public static final String REVIEW_SCHEMA_PATH="data/XSD/Review.xsd";

    public static final String NOTIFICATION_MODEL_PATH = "com.xml.publications.model.Notification";
    public static final String NOTIFICATION_COLLECTION_PATH = "/db/publications/notfication";
    public static final String NOTIFICATION_SCHEMA_PATH="data/XSD/Notification.xsd";

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

    public Workflow findWorkflowByPublication(String publicationId) throws Exception{
        Connection connection = new Connection();
        XMLResource xmlResource;

        try{
            xmlResource = connection.getResourceById(WORKFLOW_COLLECTION_PATH, publicationId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        Unmarshaller unmarshaller = getUnmarshaller(WORKFLOW_MODEL_PATH);

        try{
            return (Workflow) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));
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

    public void saveReview(Review review) throws Exception{
        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(REVIEW_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(review.getPublicationId(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(REVIEW_MODEL_PATH, REVIEW_SCHEMA_PATH);
            marshaller.marshal(review, outputStream);

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

    public void savePublication(ScientificPublication scientificPublication) throws Exception {

        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(scientificPublication.getPublicationId(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH, SCIENTIFIC_PUBLICATION_SCHEMA_PATH);
            marshaller.marshal(scientificPublication, outputStream);

            xmlResource.setContent(outputStream);
            collection.storeResource(xmlResource);
        }finally {
            if (xmlResource != null){
                try{
                    ((EXistResource) xmlResource).freeResources();
                }catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
            if (collection != null){
                try {
                    collection.close();
                } catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
        }

    }

    public void saveNotification(Notification notification) throws Exception{
        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(NOTIFICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(notification.getPublicationTitle(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(NOTIFICATION_MODEL_PATH, NOTIFICATION_SCHEMA_PATH);
            marshaller.marshal(notification, outputStream);

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

    public String getPublicationPdfById(String publicationId) throws Exception{
        Connection connection = new Connection();
        XMLResource xmlResource;

        try{
            xmlResource = connection.getResourceById(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, publicationId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        Marshaller marshaller = getMarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH, SCIENTIFIC_PUBLICATION_SCHEMA_PATH);
        Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);

        ScientificPublication sc =  (ScientificPublication) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        marshaller.marshal(sc, stream);

        return stream.toByteArray().toString();
    }

    public MessageDTO editPublication(ScientificPublicationEditDTO sp) throws Exception {
        System.out.println("edit publication");
        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);
        XMLResource xmlResource = null;
        Collection collection = null;

        try{
            collection = connection.getOrCreateCollection(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());
            xmlResource = connection.getResourceById(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, sp.getId(), AuthenticationUtilities.loadProperties());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        Marshaller marshaller = getMarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH, SCIENTIFIC_PUBLICATION_SCHEMA_PATH);
        Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
        ScientificPublication sc =  (ScientificPublication) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));


        sc.getMetadata().setTitle(sp.getTitle());

        sc.getMetadata().getKeywords().clear();
        sp.getKeywords().stream().forEach(s -> sc.getMetadata().getKeywords().add(s));

        sc.getChapter().clear();


        for(Chapter chapter: sp.getChapters()){

            ScientificPublication.Chapter cha = (new ObjectFactory().createScientificPublicationChapter());
            cha.setTitle(chapter.getTitle());

            for(Paragraph p: chapter.getParagraphs()) {
                ScientificPublication.Chapter.Paragraph newParagraph = (new ObjectFactory().createScientificPublicationChapterParagraph());
                newParagraph.setText(p.getText());

                if (p.getCitation() != null){
                    ScientificPublication.Chapter.Paragraph.Citation newCitation = (new ObjectFactory().createScientificPublicationChapterParagraphCitation());
                    //System.out.println("ovde " + p.getCitation().getId());
                    newCitation.setId(BigInteger.valueOf(Long.parseLong(p.getCitation().getId())));
                    newParagraph.setCitation(newCitation);
                }
                cha.getParagraph().add(newParagraph);

            }
            sc.getChapter().add(cha);
        }


        sc.getReference().clear();

        for (Citation referenceCitation: sp.getReferences()){
            ScientificPublication.Reference reference = (new ObjectFactory().createScientificPublicationReference());

            reference.setCitationId(BigInteger.valueOf(Long.parseLong(referenceCitation.getId())));
            reference.setPublicationTitle(referenceCitation.getPublicationTitle());
            reference.setUrl(referenceCitation.getUrl());
            reference.setYear(BigInteger.valueOf(referenceCitation.getYear()));

            reference.getAuthorNames().clear();

            for(String authorNames: referenceCitation.getAuthorNames()){
                reference.getAuthorNames().add(authorNames);
            }
            sc.getReference().add(reference);
        }


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        marshaller.marshal(sc, stream);

        xmlResource.setContent(stream);
        collection.storeResource(xmlResource);

        return new MessageDTO("Publication was changed successfully :)", true);
    }

    public void saveWorkflow(Workflow workflow) throws Exception {
        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(WORKFLOW_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(workflow.getPublicationId(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(WORKFLOW_MODEL_PATH, WORKFLOW_SCHEMA_PATH);
            marshaller.marshal(workflow, outputStream);

            xmlResource.setContent(outputStream);
            collection.storeResource(xmlResource);
        }finally {
            if (xmlResource != null){
                try{
                    ((EXistResource) xmlResource).freeResources();
                }catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
            if (collection != null){
                try {
                    collection.close();
                } catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
        }


    }

    public void saveCoverLetter(CoverLetter coverLetter) throws Exception{


        Connection connection = new Connection();
        Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
        DatabaseManager.registerDatabase(database);

        XMLResource xmlResource = null;
        Collection collection = null;

        OutputStream outputStream = new ByteArrayOutputStream();

        try{
            collection = connection.getOrCreateCollection(COVER_LETTER_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            xmlResource = (XMLResource) collection.createResource(coverLetter.getCoverLetterId(), XMLResource.RESOURCE_TYPE);

            Marshaller marshaller = getMarshaller(COVER_LETTER_MODEL_PATH, COVER_LETTER_SCHEMA_PATH);
            marshaller.marshal(coverLetter, outputStream);

            xmlResource.setContent(outputStream);
            collection.storeResource(xmlResource);
        }finally {
            if (xmlResource != null){
                try{
                    ((EXistResource) xmlResource).freeResources();
                }catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
            if (collection != null){
                try {
                    collection.close();
                } catch (XMLDBException xe){
                    xe.printStackTrace();
                }
            }
        }

    }

    public ScientificPublication getPublicationFromXMLFile(File xmlFile){
        try{
            Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
            return (ScientificPublication) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlFile));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public CoverLetter getCoverLetterFromXMLFile(File xmlFile){
        try{
            Unmarshaller unmarshaller = getUnmarshaller(COVER_LETTER_MODEL_PATH);
            return (CoverLetter) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlFile));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ScientificPublication getPublicationFromXMLString(String xmlFile){
        try{
            Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
            return (ScientificPublication) JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(xmlFile)));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public CoverLetter getCoverLetterFromXMLString(String xmlFile){
        try{
            Unmarshaller unmarshaller = getUnmarshaller(COVER_LETTER_MODEL_PATH);
            return (CoverLetter) JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(xmlFile)));
        }catch (Exception e){
            e.printStackTrace();
            return null;
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

    public List<ScientificPublication> getAllAcceptedPublications(){
        ArrayList<ScientificPublication> scientificPublications = new ArrayList<ScientificPublication>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/ScientificPublication");
            ResourceSet result = xpathService.query("//scientificPublication[@status='accepted']");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
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

    public List<User> getAllReviewers(){
        ArrayList<User> reviewers = new ArrayList<User>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(USER_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/User");
            ResourceSet result = xpathService.query("//user[role=\"EDITOR_ROLE\" or role=\"REVIEWER_ROLE\"]");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(USER_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
                    User user = (User) unmarshaller.unmarshal(reader);
                    reviewers.add(user);

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

        return reviewers;

    }

    public List<User> getAllEditors(){
        ArrayList<User> reviewers = new ArrayList<User>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(USER_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/User");
            ResourceSet result = xpathService.query("//user[role=\"EDITOR_ROLE\"]");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(USER_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
                    User user = (User) unmarshaller.unmarshal(reader);
                    reviewers.add(user);

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

        return reviewers;

    }

    public List<ScientificPublication> getPublicationsByUsername(String username) {
        ArrayList<ScientificPublication> scientificPublications = new ArrayList<ScientificPublication>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/ScientificPublication");
            ResourceSet result = xpathService.query("//scientificPublication[./metadata/authors[authorUsername='" + username + "']]");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
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

    public List<Workflow> getWorkflowsByReviewer(String username) {
        ArrayList<Workflow> workflows = new ArrayList<Workflow>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(WORKFLOW_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/Workflow");
            ResourceSet result = xpathService.query("//workflow[./reviewers[reviewerUsername='" + username + "']]");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(WORKFLOW_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
                    Workflow workflow = (Workflow) unmarshaller.unmarshal(reader);
                    workflows.add(workflow);

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

        return workflows;
    }

    public List<Workflow> getAllWorkflows(){
        ArrayList<Workflow> workflows = new ArrayList<Workflow>();
        try {
            Connection connection = new Connection();
            Database database = connection.connectToDatabase(AuthenticationUtilities.loadProperties());
            DatabaseManager.registerDatabase(database);

            Collection col = connection.getOrCreateCollection(WORKFLOW_COLLECTION_PATH, 0, AuthenticationUtilities.loadProperties());

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", "http://www.ftn.uns.ac.rs/XML/Workflow");
            ResourceSet result = xpathService.query("//workflow");

            ResourceIterator it = result.getIterator();
            Resource res = null;

            while (it.hasMoreResources()) {
                try {
                    res = it.nextResource();
                    Unmarshaller unmarshaller = getUnmarshaller(WORKFLOW_MODEL_PATH);
                    StringReader reader = new StringReader(res.getContent().toString());
                    Workflow workflow = (Workflow) unmarshaller.unmarshal(reader);
                    workflows.add(workflow);

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

        return workflows;
    }

    public ScientificPublication getPublicationById(String publicationId) throws Exception{

        Connection connection = new Connection();
        XMLResource xmlResource;

        try{
            xmlResource = connection.getResourceById(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, publicationId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        Unmarshaller unmarshaller = getUnmarshaller(SCIENTIFIC_PUBLICATION_MODEL_PATH);

        try{
            return (ScientificPublication) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));
        }catch (NullPointerException ne){
            return null;
        }
    }

    public Workflow getWorkflowById(String workflowId) throws Exception{

        Connection connection = new Connection();
        XMLResource xmlResource;

        try{
            xmlResource = connection.getResourceById(WORKFLOW_COLLECTION_PATH, workflowId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        Unmarshaller unmarshaller = getUnmarshaller(WORKFLOW_MODEL_PATH);

        try{
            return (Workflow) JAXBIntrospector.getValue(unmarshaller.unmarshal(xmlResource.getContentAsDOM()));
        }catch (NullPointerException ne){
            return null;
        }
    }


    public DOMSource getPublicationAsDom(String publicationId) throws Exception{
        Connection connection = new Connection();
        XMLResource xmlResource = null;

        try{
            xmlResource = connection.getResourceById(SCIENTIFIC_PUBLICATION_COLLECTION_PATH, publicationId, AuthenticationUtilities.loadProperties());
        }catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }

        try{
            DOMSource domSource = new DOMSource(xmlResource.getContentAsDOM());
            return domSource;
        }catch (NullPointerException ne){
            return null;
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
