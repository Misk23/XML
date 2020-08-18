package com.xml.publications.service;

import com.xml.publications.model.ScientificPublication.ScientificPublication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ScientificPublicationService {

    List<ScientificPublication> basicSearchScientificPublication(String text);
    String savePublicationFromFile(MultipartFile xmlFile) throws IOException;
    String savePublicationFromText(String xmlFile);
    List<ScientificPublication> getMyPublications(String username);
    String changePublicationStatus(String publicationId, String status);
    String showPublication(String publicationId);
}
