package com.xml.publications.repository;


import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ScientificPublicationRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private Validator validator;

    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return databaseService.basicSearchScientificPublication(text);
    }

    public void savePublication(ScientificPublication scientificPublication) throws Exception {
        databaseService.savePublication(scientificPublication);
        //TODO RDF metoda u ovu liniju koda
    }

    public String savePublicationFromFile(File xmlFile){

        ScientificPublication scientificPublication = databaseService.getPublicationFromXML(xmlFile);

        if (scientificPublication == null)
            return "Class from xml failure";

        if ( !validator.validatePublicationAgainstSchema(scientificPublication))
            return "Given publication is not valid (schema validation)";

        try{
            savePublication(scientificPublication);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving publication unsuccessful";
        }

        return "Saving publication successful";
    }


}
