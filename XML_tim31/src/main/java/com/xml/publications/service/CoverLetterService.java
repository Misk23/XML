package com.xml.publications.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CoverLetterService {

    String saveCoverLetterFromFile(MultipartFile xmlFile) throws IOException;
    String saveCoverLetterFromText(String xmlFile);
}
