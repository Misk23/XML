package com.xml.publications.DTO;

import java.util.Date;

public class ReviewDTO {

    private String publicationId;
    private Date submissionDate;
    private String reviewedBy;
    private  String relevanceOfResearchProblem;
    private String conceptualQuality;
    private String methodologicalQuality;
    private String readability;
    private String originality;
    private String overallEvaluation;
    private String commentToAuthor;
    private String commentsToEditor;

    public ReviewDTO() {
    }

    public ReviewDTO(String publicationId, Date submissionDate, String reviewedBy, String relevanceOfResearchProblem, String conceptualQuality, String methodologicalQuality, String readability, String originality, String overallEvaluation, String commentToAuthor, String commentsToEditor) {
        this.publicationId = publicationId;
        this.submissionDate = submissionDate;
        this.reviewedBy = reviewedBy;
        this.relevanceOfResearchProblem = relevanceOfResearchProblem;
        this.conceptualQuality = conceptualQuality;
        this.methodologicalQuality = methodologicalQuality;
        this.readability = readability;
        this.originality = originality;
        this.overallEvaluation = overallEvaluation;
        this.commentToAuthor = commentToAuthor;
        this.commentsToEditor = commentsToEditor;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getRelevanceOfResearchProblem() {
        return relevanceOfResearchProblem;
    }

    public void setRelevanceOfResearchProblem(String relevanceOfResearchProblem) {
        this.relevanceOfResearchProblem = relevanceOfResearchProblem;
    }

    public String getConceptualQuality() {
        return conceptualQuality;
    }

    public void setConceptualQuality(String conceptualQuality) {
        this.conceptualQuality = conceptualQuality;
    }

    public String getMethodologicalQuality() {
        return methodologicalQuality;
    }

    public void setMethodologicalQuality(String methodologicalQuality) {
        this.methodologicalQuality = methodologicalQuality;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    public String getOriginality() {
        return originality;
    }

    public void setOriginality(String originality) {
        this.originality = originality;
    }

    public String getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(String overallEvaluation) {
        this.overallEvaluation = overallEvaluation;
    }

    public String getCommentToAuthor() {
        return commentToAuthor;
    }

    public void setCommentToAuthor(String commentToAuthor) {
        this.commentToAuthor = commentToAuthor;
    }

    public String getCommentsToEditor() {
        return commentsToEditor;
    }

    public void setCommentsToEditor(String commentsToEditor) {
        this.commentsToEditor = commentsToEditor;
    }
}
