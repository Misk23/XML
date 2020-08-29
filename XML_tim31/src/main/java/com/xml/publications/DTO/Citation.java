package com.xml.publications.DTO;

import java.util.ArrayList;

public class Citation {
    private String id;
    private ArrayList<String> authorNames;
    private int year;
    private String publicationTitle;
    private String text;
    private String url;

    public Citation(){
        super();
    }

    public Citation(String id, ArrayList<String> authorNames, int year, String publicationTitle, String text, String url) {
        this.id = id;
        this.authorNames = authorNames;
        this.year = year;
        this.publicationTitle = publicationTitle;
        this.text = text;
        this.url = url;
    }

    public Citation(String id, ArrayList<String> authorNames, int year, String publicationTitle, String url) {
        this.id = id;
        this.authorNames = authorNames;
        this.year = year;
        this.publicationTitle = publicationTitle;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
