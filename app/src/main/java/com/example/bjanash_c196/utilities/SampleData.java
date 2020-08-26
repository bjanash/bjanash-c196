package com.example.bjanash_c196.utilities;

import com.example.bjanash_c196.model.AssessmentEntity;
import com.example.bjanash_c196.model.CourseEntity;
import com.example.bjanash_c196.model.NoteEntity;
import com.example.bjanash_c196.model.TermEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
//    Sample Data for Terms
    private static final String SAMPLE_TERM_TITLE = "Sample Term 1";
    private static final String SAMPLE_TERM_TITLE_2 = "Sample Term 2";
    private static final String SAMPLE_TERM_TITLE_3 = "Sample Term 3";
    private static final String SAMPLE_TERM_TITLE_4 = "Sample Term 4";
    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List<TermEntity> getTerms() {
        List<TermEntity> terms = new ArrayList<>();
        terms.add(new TermEntity(SAMPLE_TERM_TITLE, getDate(0), getDate(1)));
        terms.add(new TermEntity(SAMPLE_TERM_TITLE_2, getDate(0), getDate(1)));
        terms.add(new TermEntity(SAMPLE_TERM_TITLE_3, getDate(0), getDate(1)));
        terms.add(new TermEntity(SAMPLE_TERM_TITLE_4, getDate(0), getDate(1)));
        return terms;
    }

//    Sample Data for Courses
    private static final String SAMPLE_COURSE_TITLE = "Sample Course 1";
    private static final String SAMPLE_COURSE_TITLE_2 = "Sample Course 2";
    private static final String SAMPLE_COURSE_TITLE_3 = "Sample Course 3";
    private static final String SAMPLE_COURSE_TITLE_4 = "Sample Course 4";

    private static final String SAMPLE_COURSE_STATUS = "in progress";
    private static final String SAMPLE_COURSE_STATUS_2 = "plan to take";
    private static final String SAMPLE_COURSE_STATUS_3 = "dropped";
    private static final String SAMPLE_COURSE_STATUS_4 = "completed";

    private static final String SAMPLE_COURSE_MENTOR = "Roger Federer";
    private static final String SAMPLE_COURSE_MENTOR_2 = "Rafael Nadal";
    private static final String SAMPLE_COURSE_MENTOR_3 = "Novak Djokovic";
    private static final String SAMPLE_COURSE_MENTOR_4 = "Rod Laver";

    private static final int SAMPLE_PHONE_NUMBER = 1231234;
    private static final int SAMPLE_PHONE_NUMBER_2 = 4321321;
    private static final int SAMPLE_PHONE_NUMBER_3 = 9876543;
    private static final int SAMPLE_PHONE_NUMBER_4 = 3456789;

    private static final String SAMPLE_EMAIL_ADDRESS = "roger@federer.com";
    private static final String SAMPLE_EMAIL_ADDRESS_2 = "rafael@nadal.com";
    private static final String SAMPLE_EMAIL_ADDRESS_3 = "novak@djokovic.com";
    private static final String SAMPLE_EMAIL_ADDRESS_4 = "rod@laver.com";

    public static List<CourseEntity> getCourses() {
        List<CourseEntity> courses = new ArrayList<>();
        courses.add(new CourseEntity(SAMPLE_COURSE_TITLE, getDate(0), getDate(1),SAMPLE_COURSE_STATUS,SAMPLE_COURSE_MENTOR,SAMPLE_PHONE_NUMBER,SAMPLE_EMAIL_ADDRESS));
        courses.add(new CourseEntity(SAMPLE_COURSE_TITLE_2, getDate(0), getDate(1),SAMPLE_COURSE_STATUS_2,SAMPLE_COURSE_MENTOR_2,SAMPLE_PHONE_NUMBER_2,SAMPLE_EMAIL_ADDRESS_2));
        courses.add(new CourseEntity(SAMPLE_COURSE_TITLE_3, getDate(0), getDate(1),SAMPLE_COURSE_STATUS_3,SAMPLE_COURSE_MENTOR_3,SAMPLE_PHONE_NUMBER_3,SAMPLE_EMAIL_ADDRESS_3));
        courses.add(new CourseEntity(SAMPLE_COURSE_TITLE_4, getDate(0), getDate(1),SAMPLE_COURSE_STATUS_4,SAMPLE_COURSE_MENTOR_4,SAMPLE_PHONE_NUMBER_4,SAMPLE_EMAIL_ADDRESS_4));
        return courses;
    }

//    Sample Data for Notes
    private static final String SAMPLE_NOTE_TITLE = "Sample Note 1";
    private static final String SAMPLE_NOTE_TITLE_2 = "Sample Note 2";
    private static final String SAMPLE_NOTE_TITLE_3 = "Sample Note 3";
    private static final String SAMPLE_NOTE_TITLE_4 = "Sample Note 4";

    private static final String SAMPLE_NOTE_CONTENT = "Those who stand for nothing fall for everything.";
    private static final String SAMPLE_NOTE_CONTENT_2 = "Give all the power to the many, they will oppress the few. Give all the power to the few, they will oppress the many.";
    private static final String SAMPLE_NOTE_CONTENT_3 = "The constitution shall never be construed...to prevent the people of the United States who are peaceable citizens from keeping their own arms.";
    private static final String SAMPLE_NOTE_CONTENT_4 = "The art of reading is to skip judiciously.";

    public static List<NoteEntity> getNotes() {
        List<NoteEntity> notes = new ArrayList<>();
        notes.add(new NoteEntity(SAMPLE_NOTE_TITLE, SAMPLE_NOTE_CONTENT));
        notes.add(new NoteEntity(SAMPLE_NOTE_TITLE_2, SAMPLE_NOTE_CONTENT_2));
        notes.add(new NoteEntity(SAMPLE_NOTE_TITLE_3, SAMPLE_NOTE_CONTENT_3));
        notes.add(new NoteEntity(SAMPLE_NOTE_TITLE_4, SAMPLE_NOTE_CONTENT_4));
        return notes;
    }

//    Sample Data for Assessments
    private static final String SAMPLE_ASSESSMENT_TITLE = "Sample Assessment 1";
    private static final String SAMPLE_ASSESSMENT_TITLE_2 = "Sample Assessment 2";
    private static final String SAMPLE_ASSESSMENT_TITLE_3 = "Sample Assessment 3";
    private static final String SAMPLE_ASSESSMENT_TITLE_4 = "Sample Assessment 4";

    private static final String SAMPLE_ASSESSMENT_TYPE = "OBJECTIVE";
    private static final String SAMPLE_ASSESSMENT_TYPE_2 = "PERFORMANCE";
    private static final String SAMPLE_ASSESSMENT_TYPE_3 = "OBJECTIVE";
    private static final String SAMPLE_ASSESSMENT_TYPE_4 = "PERFORMANCE";

    public static List<AssessmentEntity> getAsessments() {
        List<AssessmentEntity> assessments = new ArrayList<>();
        assessments.add(new AssessmentEntity(SAMPLE_ASSESSMENT_TITLE, getDate(0), SAMPLE_ASSESSMENT_TYPE));
        assessments.add(new AssessmentEntity(SAMPLE_ASSESSMENT_TITLE_2, getDate(1), SAMPLE_ASSESSMENT_TYPE_2));
        assessments.add(new AssessmentEntity(SAMPLE_ASSESSMENT_TITLE_3, getDate(2), SAMPLE_ASSESSMENT_TYPE_3));
        assessments.add(new AssessmentEntity(SAMPLE_ASSESSMENT_TITLE_4, getDate(3), SAMPLE_ASSESSMENT_TYPE_4));
        return assessments;
    }
}
