package com.xml.publications.controller;


import com.xml.publications.DTO.ReviewRequestDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.Workflow.Workflow;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private ScientificPublicationService scientificPublicationService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Workflow>> getAllWorkflows(){
        return new ResponseEntity<List<Workflow>>(workflowService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> acceptPublication(@PathVariable String id) throws Exception {
        return new ResponseEntity<String>(scientificPublicationService.changePublicationStatus(id, "accepted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/reject/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> rejectPublication(@PathVariable String id) throws Exception {
        return new ResponseEntity<String>(scientificPublicationService.changePublicationStatus(id, "rejected"), HttpStatus.OK);
    }

    @RequestMapping(value = "/add_reviewer", method = RequestMethod.POST)
    public ResponseEntity<String> addReviewer(@RequestBody ReviewRequestDTO reviewRequestDTO) throws Exception {
        return new ResponseEntity<String>(workflowService.addReviewer(reviewRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/get_to_review/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<ScientificPublication>> getToReview(@PathVariable String username) throws Exception {
        return new ResponseEntity<List<ScientificPublication>>(workflowService.getPublicationsToReview(username), HttpStatus.OK);
    }
}
