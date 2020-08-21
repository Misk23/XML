package com.xml.publications.repository;

import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkflowRepository {

    @Autowired
    private DatabaseService databaseService;


    public void save(Workflow workflow) throws Exception {
        databaseService.saveWorkflow(workflow);
    }

    public List<Workflow> getAll(){
        return databaseService.getAllWorkflows();
    }

    public Workflow findByPublicationId(String publicationId) {
        try {
            return databaseService.findWorkflowByPublication(publicationId);
        } catch (Exception e) {
            System.out.println("Finding user EXCEPTION");
            return null;
        }
    }

    public void addReviewer(String publicationId, String reviewer) throws Exception {
        Workflow workflow = databaseService.getWorkflowById(publicationId);
        if (!workflow.getReviewers().getReviewerUsername().contains(reviewer)) {
            workflow.getReviewers().getReviewerUsername().add(reviewer);
            workflow.setStatus("reviewing");
        }
        save(workflow);
    }

    public List<ScientificPublication> getPublicationsToReview(String username) throws Exception {
        ArrayList<ScientificPublication> scientificPublications = new ArrayList<ScientificPublication>();

        List<Workflow> workflows = databaseService.getWorkflowsByReviewer(username);
        for (Workflow w : workflows){
            scientificPublications.add(databaseService.getPublicationById(w.getPublicationId()));
            System.out.println(w.getPublicationId());
        }

        return scientificPublications;


    }


}
