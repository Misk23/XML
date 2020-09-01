package com.xml.publications.service;

import com.xml.publications.DTO.ReviewDTO;
import org.springframework.core.io.ByteArrayResource;

public interface ReviewService {

    String saveReview(ReviewDTO reviewDTO) throws Exception;
    ByteArrayResource getPublicationWithReviews(String publicationId) throws Exception;


}
