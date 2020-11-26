package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessments")
public class AssessmentEntity {
    @PrimaryKey @NonNull
    private String assessmentTitle;
    private String assessmentDueDate;
    private String assessmentType;

    public AssessmentEntity(String assessmentTitle, String assessmentDueDate, String assessmentType) {
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentType = assessmentType;
    }

    @Ignore
    public AssessmentEntity() {
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(String assessmentDueDate) {
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

