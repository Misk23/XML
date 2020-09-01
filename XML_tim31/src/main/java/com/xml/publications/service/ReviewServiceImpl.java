package com.xml.publications.service;

import com.xml.publications.DTO.ReviewDTO;
import com.xml.publications.model.Review.ObjectFactory;
import com.xml.publications.model.Review.Review;
import com.xml.publications.model.Review.TCriteriaGrade;
import com.xml.publications.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public String saveReview(ReviewDTO reviewDTO) throws Exception {

        Review review = (new ObjectFactory()).createReview();

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(reviewDTO.getSubmissionDate());
        XMLGregorianCalendar sDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        review.setSubmissionDate(sDate);
        review.setPublicationId(reviewDTO.getPublicationId());
        review.setReviewedBy(reviewDTO.getReviewedBy());
        Review.CriteriaEvaluation criteriaEvaluation = new ObjectFactory().createReviewCriteriaEvaluation();
        criteriaEvaluation.setRelevanceOfResearchProblem(TCriteriaGrade.fromValue(reviewDTO.getRelevanceOfResearchProblem().toLowerCase().replace("_", " ")));
        criteriaEvaluation.setConceptualQuality(TCriteriaGrade.fromValue(reviewDTO.getConceptualQuality().toLowerCase().replace("_", " ")));
        criteriaEvaluation.setMethodologicalQuality(TCriteriaGrade.fromValue(reviewDTO.getMethodologicalQuality().toLowerCase().replace("_", " ")));
        criteriaEvaluation.setReadability(TCriteriaGrade.fromValue(reviewDTO.getReadability().toLowerCase().replace("_", " ")));
        criteriaEvaluation.setOriginality(TCriteriaGrade.fromValue(reviewDTO.getOriginality().toLowerCase().replace("_", " ")));

        review.setCriteriaEvaluation(criteriaEvaluation);
        review.setOverallEvaluation(reviewDTO.getOverallEvaluation());
        review.setCommentToAuthor(reviewDTO.getCommentToAuthor());
        review.setCommentsToEditor(reviewDTO.getCommentsToEditor());

        System.out.println("REVIEW /////////////////");
        System.out.println(review.getSubmissionDate());
        System.out.println(review.getPublicationId());
        System.out.println(review.getReviewedBy());

        System.out.println(criteriaEvaluation.getRelevanceOfResearchProblem());
        System.out.println(criteriaEvaluation.getConceptualQuality());
        System.out.println(criteriaEvaluation.getMethodologicalQuality());
        System.out.println(criteriaEvaluation.getOriginality());
        System.out.println(criteriaEvaluation.getReadability());

        System.out.println(review.getOverallEvaluation());
        System.out.println(review.getCommentToAuthor());
        System.out.println(review.getCommentsToEditor());

        return reviewRepository.saveReview(review);

    }

    public ByteArrayResource getPublicationWithReviews(String publicationId) throws Exception {
        return reviewRepository.getPublicationWithReviews(publicationId);
    }
}
