package com.xml.publications.controller;


import com.xml.publications.model.ScientificPublication.ScientificPublication;
import com.xml.publications.service.ScientificPublicationService;
import com.xml.publications.utils.Database.DatabaseService;
import com.xml.publications.utils.Transformer.PDFTransformer;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.dom.DOMSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping(value = "/scientificPublication")
public class ScientificPublicationController {

    @Autowired
    private ScientificPublicationService scientificPublicationService;

    @Autowired
    private DatabaseService databaseService;


    @RequestMapping(
            method = RequestMethod.GET,
            value="/basicSearch",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScientificPublication>> basicSearch(@RequestParam("text") String text){
        return new ResponseEntity<List<ScientificPublication>>(scientificPublicationService.basicSearchScientificPublication(text), HttpStatus.OK);
    }

    @RequestMapping(value = "/save_as_XML", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity savePublicationFromFile(@RequestParam("file") MultipartFile xmlFile){
        String response;
        try{
            response = scientificPublicationService.savePublicationFromFile(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid publication request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/save_as_XMLString", method = RequestMethod.POST)
    public ResponseEntity savePublicationFromText(@RequestBody String xmlFile){
        String response;
        try{
            response = scientificPublicationService.savePublicationFromText(xmlFile);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid publication request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/my_publications/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<ScientificPublication>> getMyPublications(@PathVariable String username){
        return new ResponseEntity<List<ScientificPublication>>(scientificPublicationService.getMyPublications(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/showPublication/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> showPublication(@PathVariable String id){

        String path = scientificPublicationService.showPublication(id);

        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new BufferedReader(new FileReader(path));
            String line = "";
            while((line = in.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/getScientificPublicationPDF/{id}",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<ByteArrayResource> getScientificPublicationPDF(@PathVariable String id) throws Exception {
        ResponseEntity<String> generatePdf = showPublication(id);
        DOMSource domSource = databaseService.getPublicationAsDom(id);
        PDFTransformer pdfTransformer = new PDFTransformer();
        InputStreamResource inputStreamResource = pdfTransformer.getPdf(domSource);
        return new ResponseEntity<>(new ByteArrayResource(IOUtils.toByteArray(inputStreamResource.getInputStream())), HttpStatus.OK);
    }

    @RequestMapping(value = "/withdraw/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> withdrawPublication(@PathVariable String id) throws Exception {
        return new ResponseEntity<String>(scientificPublicationService.changePublicationStatus(id, "withdrawn"), HttpStatus.OK);
    }

    @RequestMapping(value = "/all_accepted", method = RequestMethod.GET)
    public ResponseEntity<List<ScientificPublication>> allAcceptedPublications() throws Exception {
        return new ResponseEntity<List<ScientificPublication>>(scientificPublicationService.getAllAcceptedPublications(), HttpStatus.OK);
    }


}
