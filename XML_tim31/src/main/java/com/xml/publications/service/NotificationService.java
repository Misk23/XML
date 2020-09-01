package com.xml.publications.service;

import com.xml.publications.DTO.ReviewDTO;
import com.xml.publications.DTO.ReviewRequestDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;

public interface NotificationService {

    void notificationPublicationSubmitted(ScientificPublication scientificPublication) throws Exception;
    void notificationPublicationAccepted(String publicationId) throws Exception;
    void notificationPublicationRejected(String publicationId) throws Exception;
    void notificationPublicationWithdrawn(String publicationId) throws Exception;
    void notificationReviewerAssigned(ReviewRequestDTO reviewRequestDTO) throws Exception;
    void notificationReviewSubmitted(ReviewDTO reviewDTO) throws Exception;
}
