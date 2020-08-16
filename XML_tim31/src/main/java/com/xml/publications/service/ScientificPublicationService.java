package com.xml.publications.service;

import com.xml.publications.model.ScientificPublication.ScientificPublication;

import java.util.ArrayList;
import java.util.List;

public interface ScientificPublicationService {

    List<ScientificPublication> basicSearchScientificPublication(String text);
}
