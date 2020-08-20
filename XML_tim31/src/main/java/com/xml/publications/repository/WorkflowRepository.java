package com.xml.publications.repository;

import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
