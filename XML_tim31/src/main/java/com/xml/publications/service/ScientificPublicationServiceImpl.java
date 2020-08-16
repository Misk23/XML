package com.xml.publications.service;

import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.repository.ScientificPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScientificPublicationServiceImpl implements ScientificPublicationService {


    @Autowired
    private ScientificPublicationRepository scientificPublicationRepository;


    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return scientificPublicationRepository.basicSearchScientificPublication(text);
    }
}
