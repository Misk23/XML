package com.xml.publications.controller;


import com.xml.publications.service.CoverLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/coverLetter")
public class CoverLetterController {

    @Autowired
    private CoverLetterService coverLetterService;

    @RequestMapping(value = "/save_as_XML", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity saveCoverLetterFromFile(@RequestParam("file") MultipartFile xmlFile){
        String response;
        try{
            response = coverLetterService.saveCoverLetterFromFile(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid cover letter request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/save_as_XMLString", method = RequestMethod.POST)
    public ResponseEntity saveCoverLetterFromText(@RequestBody String xmlFile){
        String response;
        try{
            response = coverLetterService.saveCoverLetterFromText(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid cover letter request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }
}
