package com.xml.publications.repository;


import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.utils.Database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScientificPublicationRepository {

    @Autowired
    private DatabaseService databaseService;

    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return databaseService.basicSearchScientificPublication(text);
    }

}
