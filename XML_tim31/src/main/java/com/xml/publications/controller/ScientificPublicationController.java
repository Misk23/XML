package com.xml.publications.controller;


import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.service.ScientificPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/scientificPublication")
public class ScientificPublicationController {

    @Autowired
    private ScientificPublicationService scientificPublicationService;


    @RequestMapping(
            method = RequestMethod.GET,
            value="/basicSearch",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScientificPublication>> basicSearch(@RequestParam("text") String text){
        return new ResponseEntity<List<ScientificPublication>>(scientificPublicationService.basicSearchScientificPublication(text), HttpStatus.OK);
    }

    @RequestMapping(value = "/save_as_XML", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity SavePublicationFromFile(@RequestParam("file") MultipartFile xmlFile){
        String response;
        try{
            response = scientificPublicationService.SavePublicationFromFile(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid publication request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }


}
