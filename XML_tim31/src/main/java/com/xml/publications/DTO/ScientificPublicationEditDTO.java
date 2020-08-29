package com.xml.publications.DTO;

import java.util.ArrayList;

public class ScientificPublicationEditDTO {
    private String id;
    private String title;
    private ArrayList<String> keywords;
    private ArrayList<Chapter> chapters;
    private ArrayList<Citation> references;

    public ScientificPublicationEditDTO(){
        super();
    }

    public ScientificPublicationEditDTO(String id, String title, ArrayList<String> keywords, ArrayList<Chapter> chapters) {
        this.id = id;
        this.title = title;
        this.keywords = keywords;
        this.chapters = chapters;
    }

    public ScientificPublicationEditDTO(String id, String title, ArrayList<String> keywords, ArrayList<Chapter> chapters, ArrayList<Citation> references) {
        this.id = id;
        this.title = title;
        this.keywords = keywords;
        this.chapters = chapters;
        this.references = references;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public ArrayList<Citation> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<Citation> references) {
        this.references = references;
    }
}
