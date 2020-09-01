package com.xml.publications.controller;

import com.xml.publications.DTO.ReviewDTO;
import com.xml.publications.service.NotificationService;
import com.xml.publications.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private NotificationService notificationService;

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
}
