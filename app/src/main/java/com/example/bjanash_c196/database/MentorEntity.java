package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

//Fields & Table
@Entity(tableName = "mentors",
                foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "courseId",
                childColumns = "courseIdFk",
                onDelete = CASCADE))

public class MentorEntity {

    @PrimaryKey(autoGenerate = true) @NonNull
    private int mentorId;
    @ColumnInfo(name = "courseIdFk", index = true)
    private int courseIdFk;
    @ColumnInfo(name = "mentorName")
    private String mentorName;
    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;
    @ColumnInfo(name = "emailAddress")
    private String emailAddress;

    public MentorEntity(int mentorId, int courseIdFk, String mentorName, String phoneNumber, String emailAddress) {
        this.mentorId = mentorId;
        this.courseIdFk = courseIdFk;
        this.mentorName = mentorName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Ignore
    public MentorEntity() {
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(int courseIdFk) {
        this.courseIdFk = courseIdFk;
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

    @Override
    public String toString() {
        return "MentorEntity{" +
                "mentorId=" + mentorId +
                ", courseIdFk=" + courseIdFk +
                ", mentorName='" + mentorName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

