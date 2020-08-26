package com.example.bjanash_c196.model;

import java.util.Date;

public class TermEntity {
    private String termTitle;
    private Date termStartDate;
    private Date termEndDate;

    public TermEntity(String termTitle, Date termStartDate, Date termEndDate) {
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public TermEntity() {
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

    @Override
    public String toString() {
        return "TermEntity{" +
                "termTitle='" + termTitle + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                '}';
    }
}
