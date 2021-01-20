package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;
//Fields & Table
@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "courseId",
                childColumns = "courseIdFk",
                onDelete = CASCADE))

//Constructors
public class AssessmentEntity {
    @PrimaryKey (autoGenerate = true) @NonNull
    private int assessmentId;
    @ColumnInfo(name = "courseIdFk", index = true)
    private int courseIdFk;
    @ColumnInfo(name = "assessmentTitle")
    private String assessmentTitle;
    @ColumnInfo(name = "assessmentDate")
    private Date assessmentDueDate;
    @ColumnInfo(name = "assessmentType")
    private String assessmentType;
    @ColumnInfo(name = "assessmentStatus")
    private String assessmentStatus;

    public AssessmentEntity(int assessmentId, int courseIdFk, String assessmentTitle, Date assessmentDueDate, String assessmentType, String assessmentStatus) {
        this.assessmentId = assessmentId;
        this.courseIdFk = courseIdFk;
        this.assessmentTitle = assessmentTitle;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentType = assessmentType;
        this.assessmentStatus= assessmentStatus;
    }

    @Ignore
    public AssessmentEntity() {
    }

//Getters & Setters
    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(int courseIdFk) {
        this.courseIdFk = courseIdFk;
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

    public String getAssessmentStatus() {
        return assessmentStatus;
    }

    public void setAssessmentStatus(String assessmentStatus) {
        this.assessmentStatus = assessmentStatus;
    }

//To String
    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentId=" + assessmentId +
                ", courseIdFk=" + courseIdFk +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentDueDate=" + assessmentDueDate +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}

