package com.xml.publications.service;

import com.xml.publications.DTO.ReviewDTO;
import com.xml.publications.DTO.ReviewRequestDTO;
import com.xml.publications.model.Notification.Notification;
import com.xml.publications.model.Notification.ObjectFactory;
import com.xml.publications.model.Notification.TParticipant;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.repository.NotificationRepository;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DatabaseService databaseService;

    public void notificationPublicationSubmitted(ScientificPublication scientificPublication) throws Exception {

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Dodata nova publikacija");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("revision submitted");

        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getUserById(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(1)).getEmail());
        sender.setUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0));
        sender.setRole("AUTHOR_ROLE");

        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getAllEditors().get(0).getEmail());
        receiver.setUsername(databaseService.getAllEditors().get(0).getUsername());
        receiver.setRole("EDITOR_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        System.out.println("Notfikacija: -------------------------------");
        System.out.println(notification.getSubmissionDate());
        System.out.println(notification.getType());
        System.out.println(notification.getPublicationTitle());
        System.out.println(notification.getContent());
        System.out.println(notification.getReceiver().getUsername());
        System.out.println(notification.getReceiver().getEmail());
        System.out.println(notification.getReceiver().getRole());
        System.out.println(notification.getSender().getUsername());
        System.out.println(notification.getSender().getEmail());
        System.out.println(notification.getSender().getRole());

        /*emailService.sendEmail("New publication submitted", notification.getReceiver().getUsername()+ "\tcheckout new publication"
               , notification.getReceiver().getEmail());*/
        notificationRepository.saveNotification(notification);
    }

    public void notificationPublicationAccepted(String publicationId) throws Exception{
        ScientificPublication scientificPublication = databaseService.getPublicationById(publicationId);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Publikacija prihvacena");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("publication accepted");

        //editor i autor moraju da postoje u sistemu
        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getAllEditors().get(0).getEmail());
        sender.setUsername(databaseService.getAllEditors().get(0).getUsername());
        sender.setRole("EDITOR_ROLE");

        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getUserById(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0)).getEmail());
        receiver.setUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0));
        receiver.setRole("AUTHOR_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        /*emailService.sendEmail("Publication Accepted", notification.getReceiver().getUsername()+ "\tYour publication has been accepted"
                , notification.getReceiver().getEmail());*/

        notificationRepository.saveNotification(notification);


    }

    public void notificationPublicationRejected(String publicationId) throws Exception{
        ScientificPublication scientificPublication = databaseService.getPublicationById(publicationId);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Publikacija odbijena");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("publication rejected");

        //editor i autor moraju da postoje u sistemu
        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getAllEditors().get(0).getEmail());
        sender.setUsername(databaseService.getAllEditors().get(0).getUsername());
        sender.setRole("EDITOR_ROLE");

        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getUserById(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0)).getEmail());
        receiver.setUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0));
        receiver.setRole("AUTHOR_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        /*emailService.sendEmail("Publication rejected", notification.getReceiver().getUsername()+ "\tYour publication has been rejected"
                , notification.getReceiver().getEmail());*/

        notificationRepository.saveNotification(notification);


    }

    public void notificationPublicationWithdrawn(String publicationId) throws Exception{
        ScientificPublication scientificPublication = databaseService.getPublicationById(publicationId);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Publikacija povucena");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("publication withdrawn");

        //editor i autor moraju da postoje u sistemu
        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getAllEditors().get(0).getEmail());
        receiver.setUsername(databaseService.getAllEditors().get(0).getUsername());
        receiver.setRole("EDITOR_ROLE");

        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getUserById(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0)).getEmail());
        sender.setUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0));
        sender.setRole("AUTHOR_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        /*emailService.sendEmail("Publication withdrawn", notification.getReceiver().getUsername()+ "\tPublication has been withdrawn"
                , notification.getReceiver().getEmail());

        notificationRepository.saveNotification(notification);*/


    }

    public void notificationReviewerAssigned(ReviewRequestDTO reviewRequestDTO) throws Exception{
        ScientificPublication scientificPublication = databaseService.getPublicationById(reviewRequestDTO.getPublication());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Dodat review");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("review submitted");

        //author i reviewer moraju da postoje u sistemu
        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getAllEditors().get(0).getEmail());
        sender.setUsername(databaseService.getAllEditors().get(0).getUsername());
        sender.setRole("EDITOR_ROLE");

        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getUserById(reviewRequestDTO.getReviewer()).getEmail());
        receiver.setUsername(reviewRequestDTO.getReviewer());
        receiver.setRole("REVIEWER_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        /*emailService.sendEmail("Publication assigned", notification.getReceiver().getUsername()+ "\tPlease review assigned publication"
                , notification.getReceiver().getEmail());*/

        notificationRepository.saveNotification(notification);


    }

    public void notificationReviewSubmitted(ReviewDTO reviewDTO) throws Exception{
        ScientificPublication scientificPublication = databaseService.getPublicationById(reviewDTO.getPublicationId());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Notification notification = (new ObjectFactory()).createNotification();
        notification.setContent("Dodat reviewer");
        notification.setPublicationTitle(scientificPublication.getMetadata().getTitle());
        notification.setSubmissionDate(sDate);
        notification.setType("reviewer assigned");

        //editor i reviewer moraju da postoje u sistemu
        TParticipant sender = (new ObjectFactory()).createTParticipant();
        sender.setEmail(databaseService.getUserById(reviewDTO.getReviewedBy()).getEmail());
        sender.setUsername(reviewDTO.getReviewedBy());
        sender.setRole("REVIEWER_ROLE");

        TParticipant receiver = (new ObjectFactory()).createTParticipant();
        receiver.setEmail(databaseService.getUserById(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0)).getEmail());
        receiver.setUsername(scientificPublication.getMetadata().getAuthors().getAuthorUsername().get(0));
        receiver.setRole("AUTHOR_ROLE");

        notification.setReceiver(receiver);
        notification.setSender(sender);

        /*emailService.sendEmail("Review Submitted", notification.getReceiver().getUsername()+ "\tReview has been submitted for your publication"
                , notification.getReceiver().getEmail());*/

        notificationRepository.saveNotification(notification);


    }

}
