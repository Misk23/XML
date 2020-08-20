package com.xml.publications.service;

import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;

    public List<Workflow> getAll(){
        return workflowRepository.getAll();
    }

}
