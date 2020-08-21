package com.xml.publications.service;

import com.xml.publications.DTO.ReviewRequestDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.repository.ScientificPublicationRepository;
import com.xml.publications.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private ScientificPublicationRepository scientificPublicationRepository;

    public List<Workflow> getAll(){
        return workflowRepository.getAll();
    }

    public String addReviewer(ReviewRequestDTO reviewRequestDTO) throws Exception {

        System.out.println(reviewRequestDTO.getPublication());
        System.out.println(reviewRequestDTO.getReviewer());

        scientificPublicationRepository.changeStatus(reviewRequestDTO.getPublication(), "reviewing");
        workflowRepository.addReviewer(reviewRequestDTO.getPublication(), reviewRequestDTO.getReviewer());

        return "Successful";
    }

    public List<ScientificPublication> getPublicationsToReview(String username) throws Exception {
        return workflowRepository.getPublicationsToReview(username);
    }


}
