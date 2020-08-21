package com.xml.publications.DTO;

public class ReviewRequestDTO {

    String publication;
    String reviewer;

    public ReviewRequestDTO() {
    }

    public ReviewRequestDTO(String publication, String reviewer) {
        this.publication = publication;
        this.reviewer = reviewer;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
