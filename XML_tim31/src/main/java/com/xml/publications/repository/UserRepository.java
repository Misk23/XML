package com.xml.publications.repository;

import com.xml.publications.model.User.User;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    @Autowired
    DatabaseService databaseService;

    @Autowired
    Validator validator;

    public String saveUser(User user){

        if(!validator.validateUserAgainstSchema(user))
            return "Given user is not valid (schema validation)";

        try{
            if (databaseService.getUserById(user.getUsername()) != null)
                return "Username already taken";

            databaseService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving user unsuccessful";
        }
        return "Saving user successful";
    }

    public User findByUsername(String username) {
        try {
            return databaseService.getUserById(username);
        } catch (Exception e) {
            System.out.println("Finding user EXCEPTION");
            return null;
        }
    }

    public List<String> getAllReviewers(){

        List<User> reviewers = databaseService.getAllReviewers();
        ArrayList<String> reviewerUsername = new ArrayList<String>();

        for(User user: reviewers){
            reviewerUsername.add(user.getUsername());
        }

        return reviewerUsername;

    }
}
