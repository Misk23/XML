package com.xml.publications.controller;

import com.xml.publications.DTO.ReviewDTO;
import com.xml.publications.model.Review.Review;
import com.xml.publications.service.NotificationService;
import com.xml.publications.service.ReviewService;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ScientificPublicationService scientificPublicationService;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveReview(@RequestBody ReviewDTO reviewDTO){
        String response;
        try{
            response = reviewService.saveReview(reviewDTO);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid review request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }


    @RequestMapping(value = "/getPublicationWithReviews/{publicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> getPublicationWithReviews(@PathVariable String publicationId) throws Exception{

        String generatePdf = scientificPublicationService.showPublication(publicationId);
        ByteArrayResource byteArrayResource = reviewService.getPublicationWithReviews(publicationId);

        return new ResponseEntity<ByteArrayResource>(byteArrayResource, HttpStatus.OK);
    }
}
