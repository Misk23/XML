package com.xml.publications.repository;


import com.xml.publications.model.CoverLetter.CoverLetter;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Database.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CoverLetterRepository {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private Validator validator;

    public String saveCoverLetterFromFile(File xmlFile){

        CoverLetter coverLetter = databaseService.getCoverLetterFromXMLFile(xmlFile);

        if (coverLetter == null)
            return "Cover letter class from xml file failure";

        if ( !validator.validateCoverLetterAgainstSchema(coverLetter))
            return "Given cover letter is not valid (schema validation)";

        try{
            databaseService.saveCoverLetter(coverLetter);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving cover letter unsuccessful";
        }

        return "Saving cover letter successful";
    }

    public String saveCoverLetterFromText(String xmlFile){

        CoverLetter coverLetter = databaseService.getCoverLetterFromXMLString(xmlFile);
        if (coverLetter == null)
            return "Cover letter class from xml string failure";

        if ( !validator.validateCoverLetterAgainstSchema(coverLetter))
            return "Given cover letter is not valid (schema validation)";
        try{
            databaseService.saveCoverLetter(coverLetter);
        }catch (Exception e){
            e.printStackTrace();
            return "Saving cover letter unsuccessful";
        }

        return "Saving cover letter successful";
    }
}
