package com.xml.publications.repository;

import com.xml.publications.model.User.User;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    @Autowired
    DatabaseService databaseService;

    @Autowired
    Validator validator;

    public String saveUser(User user){

        if(!validator.validateUserAgainstSchema(user))
            return "Given user is not valid";

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
}