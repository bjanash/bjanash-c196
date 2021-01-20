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
@Entity(tableName = "courses",
        foreignKeys = @ForeignKey(entity = TermEntity.class,
        parentColumns = "termId",
        childColumns = "termIdFk",
        onDelete = CASCADE))

public class CourseEntity {
    @PrimaryKey (autoGenerate = true) @NonNull
    private int courseId;
    @ColumnInfo(name = "termIdFk", index = true)
    private int termIdFk;
    @ColumnInfo(name = "courseTitle")
    private String courseTitle;
    @ColumnInfo(name = "courseStart")
    private Date courseStart;
    @ColumnInfo(name = "courseEnd")
    private Date courseEnd;
    @ColumnInfo(name = "courseAlertDate")
    private Date courseAlertDate;
    @ColumnInfo(name = "courseStatus")
    private String courseStatus;
    @ColumnInfo(name = "mentorName")
    private String mentorName;
    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;
    @ColumnInfo(name = "emailAddress")
    private String emailAddress;

//Constructors
    public CourseEntity(int courseId, int termIdFk, String courseTitle, Date courseStart, Date courseEnd, Date courseAlertDate, String courseStatus, String mentorName, String phoneNumber, String emailAddress) {
        this.courseId = courseId;
        this.termIdFk = termIdFk;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseAlertDate = courseAlertDate;
        this.courseStatus = courseStatus;
        this.mentorName = mentorName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Ignore
    public CourseEntity() {
    }

//Getters & Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermIdFk() {
        return termIdFk;
    }

    public void setTermIdFk(int termIdFk) {
        this.termIdFk = termIdFk;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Date courseStart) {
        this.courseStart = courseStart;
    }

    public Date getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(Date courseEnd) {
        this.courseEnd = courseEnd;
    }

    public Date getCourseAlertDate() {
        return courseAlertDate;
    }

    public void setCourseAlertDate(Date courseAlertDate) {
        this.courseAlertDate = courseAlertDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

//To String
    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", termIdFk=" + termIdFk +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStart=" + courseStart +
                ", courseEnd=" + courseEnd +
                ", courseAlertDate=" + courseAlertDate +
                ", courseStatus='" + courseStatus + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
