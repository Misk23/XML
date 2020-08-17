package com.xml.publications.service;

import com.xml.publications.repository.CoverLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    public String saveCoverLetterFromFile(MultipartFile xmlFile) throws IOException {

        File xmlFileContents;
        xmlFileContents = new ClassPathResource("data/coverletterpomfile.txt").getFile();
        xmlFileContents.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(xmlFileContents);
        fileOutputStream.write(xmlFile.getBytes());
        fileOutputStream.close();

        return coverLetterRepository.saveCoverLetterFromFile(xmlFileContents);
    }

    public String saveCoverLetterFromText(String xmlFile){
        return coverLetterRepository.saveCoverLetterFromText(xmlFile);
    }
}
