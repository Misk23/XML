package com.xml.publications.repository;

import com.xml.publications.model.Review.Review;
import com.xml.publications.model.User.User;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private ScientificPublicationService scientificPublicationService;

    @Autowired
    private Validator validator;

    public String saveReview(Review review) throws Exception {

        if(!validator.validateReviewAgainstSchema(review))
            return "Given review is not valid (schema validation)";
        databaseService.saveReview(review);

        Workflow workflow = databaseService.getWorkflowById(review.getPublicationId());
        workflow.getReviewers().getReviewerUsername().remove(review.getReviewedBy());
        if (workflow.getReviewers().getReviewerUsername().isEmpty()) {
            workflow.setStatus("revising");
            scientificPublicationService.changePublicationStatus(review.getPublicationId(), "revising");

        }

        databaseService.saveWorkflow(workflow);
        return "Review saved successfully";
    }

    public User findByUsername(String username) {
        try {
            return databaseService.getUserById(username);
        } catch (Exception e) {
            System.out.println("Finding user EXCEPTION");
            return null;
        }
    }
}
