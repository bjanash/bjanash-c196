package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

//Fields & Table
@Entity(tableName = "terms")
public class TermEntity {
    @PrimaryKey(autoGenerate = true) @NonNull
    private int termId;
    @ColumnInfo(name = "termTitle")
    private String termTitle;
    @ColumnInfo(name = "termStartDate")
    private Date termStartDate;
    @ColumnInfo(name = "termEndDate")
    private Date termEndDate;

//Constructors
    public TermEntity(int termId, String termTitle, Date termStartDate, Date termEndDate) {
        this.termId = termId;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    @Ignore
    public TermEntity() {
    }

//Getters & Setters
    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public Date getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(Date termEndDate) {
        this.termEndDate = termEndDate;
    }

//To String
    @Override
    public String toString() {
        return "TermEntity{" +
                "termId=" + termId +
                ", termTitle='" + termTitle + '\'' +
                ", termStartDate='" + termStartDate + '\'' +
                ", termEndDate='" + termEndDate + '\'' +
                '}';
    }
}
