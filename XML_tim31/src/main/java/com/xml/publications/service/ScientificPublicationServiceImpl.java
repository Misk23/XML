package com.xml.publications.service;

import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.repository.ScientificPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ScientificPublicationServiceImpl implements ScientificPublicationService {


    @Autowired
    private ScientificPublicationRepository scientificPublicationRepository;


    public List<ScientificPublication> basicSearchScientificPublication(String text){
        return scientificPublicationRepository.basicSearchScientificPublication(text);
    }

    public String savePublicationFromFile(MultipartFile xmlFile) throws IOException {

        File xmlFileContents;
        xmlFileContents = new ClassPathResource("data/pubicationpomfile.txt").getFile();
        xmlFileContents.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(xmlFileContents);
        fileOutputStream.write(xmlFile.getBytes());
        fileOutputStream.close();

        return scientificPublicationRepository.savePublicationFromFile(xmlFileContents);
    }

    public String savePublicationFromText(String xmlFile){
        return scientificPublicationRepository.savePublicationFromText(xmlFile);
    }

    public List<ScientificPublication> getMyPublications(String username){
        return scientificPublicationRepository.getMyPublications(username);
    }

    public  String changePublicationStatus(String publicationId, String status){
        return scientificPublicationRepository.changeStatus(publicationId, status);
    }

}