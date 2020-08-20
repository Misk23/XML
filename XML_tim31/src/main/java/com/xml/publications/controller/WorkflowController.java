package com.xml.publications.controller;


import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Workflow>> getAllWorkflows(){
        return new ResponseEntity<List<Workflow>>(workflowService.getAll(), HttpStatus.OK);
    }
}
