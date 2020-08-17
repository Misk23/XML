package com.xml.publications.controller;


import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.service.ScientificPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity savePublicationFromFile(@RequestParam("file") MultipartFile xmlFile){
        String response;
        try{
            response = scientificPublicationService.savePublicationFromFile(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid publication request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/save_as_XMLString", method = RequestMethod.POST)
    public ResponseEntity savePublicationFromText(@RequestBody String xmlFile){
        String response;
        try{
            response = scientificPublicationService.savePublicationFromText(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid publication request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }


}
