package com.xml.publications.service;

import com.xml.publications.DTO.ReviewDTO;

public interface ReviewService {

    String saveReview(ReviewDTO reviewDTO) throws Exception;
}
