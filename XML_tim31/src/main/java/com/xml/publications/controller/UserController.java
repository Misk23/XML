package com.xml.publications.controller;

import com.xml.publications.DTO.UserDTO;
import com.xml.publications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        String response;
        try{
            response = userService.registerUser(userDTO);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid registration request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }
}
