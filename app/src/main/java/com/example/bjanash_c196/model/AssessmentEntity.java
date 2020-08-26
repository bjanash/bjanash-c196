package com.example.bjanash_c196.model;

import java.util.Date;

public class AssessmentEntity {
    private String assessmentTitle;
    private Date assessmentDueDate;
    private String assessmentType;

    public AssessmentEntity(String assessmentTitle, Date assessmentDueDate, String assessmentType) {
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentType = assessmentType;
    }

    public AssessmentEntity() {
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public Date getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(Date assessmentDueDate) {
        this.assessmentDueDate = assessmentDueDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentDueDate=" + assessmentDueDate +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}

