package com.xml.publications.DTO;

public class Paragraph {
    private String text;
    private Citation citation;

    public Paragraph(){
        super();
    }


    public Paragraph(String text, Citation citation) {
        this.text = text;
        this.citation = citation;
    }

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Citation getCitation() {
        return citation;
    }

    public void setCitation(Citation citation) {
        this.citation = citation;
    }
}
