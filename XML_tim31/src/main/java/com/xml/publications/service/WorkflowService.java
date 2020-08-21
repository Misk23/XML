package com.xml.publications.service;

import com.xml.publications.DTO.ReviewRequestDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.Workflow.Workflow;

import java.util.List;

public interface WorkflowService {

    List<Workflow> getAll();
    String addReviewer(ReviewRequestDTO reviewRequestDTO) throws Exception;
    List<ScientificPublication> getPublicationsToReview(String username) throws Exception;
}
