package com.xml.publications.DTO;

public class PublicationSearchDTO {
    private String title;
    private String author;
    private String keyword;
    private String text;
    private String chapterTitle;
    private String authorCitation;
    private String publicationTitle;

    public PublicationSearchDTO(){

    }

    public PublicationSearchDTO(String title, String author, String keyword, String text, String chapterTitle, String authorCitation, String publicationTitle) {
        this.title = title;
        this.author = author;
        this.keyword = keyword;
        this.text = text;
        this.chapterTitle = chapterTitle;
        this.authorCitation = authorCitation;
        this.publicationTitle = publicationTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getAuthorCitation() {
        return authorCitation;
    }

    public void setAuthorCitation(String authorCitation) {
        this.authorCitation = authorCitation;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }
}
