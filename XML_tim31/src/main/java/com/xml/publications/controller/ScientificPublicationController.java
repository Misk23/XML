package com.xml.publications.controller;


import com.xml.publications.DTO.UserDTO;
import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.model.User.User;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
