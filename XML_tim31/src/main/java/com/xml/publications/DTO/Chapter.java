package com.xml.publications.DTO;

import java.util.ArrayList;

public class Chapter {
    private String title;
    private ArrayList<Paragraph> paragraphs;

    public Chapter(){
        super();
    }

    public Chapter(String title, ArrayList<Paragraph> paragraphs) {
        this.title = title;
        this.paragraphs = paragraphs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
