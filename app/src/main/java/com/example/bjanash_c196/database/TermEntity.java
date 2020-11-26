package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "terms")
public class TermEntity {
    @PrimaryKey @NonNull
    private String termTitle;
    private String termStartDate;
    private String termEndDate;

    public TermEntity(String termTitle, String termStartDate, String termEndDate) {
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    @Ignore
    public TermEntity() {
    }


    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termTitle='" + termTitle + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                '}';
    }
}
