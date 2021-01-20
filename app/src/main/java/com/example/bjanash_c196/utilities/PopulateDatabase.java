package com.example.bjanash_c196.utilities;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;

import java.util.Calendar;
import java.util.List;

public class PopulateDatabase extends AppCompatActivity {
    public static String LOG_TAG = "PopData";

    TermEntity tempTerm1 = new TermEntity();
    TermEntity tempTerm2 = new TermEntity();
    TermEntity tempTerm3 = new TermEntity();

    CourseEntity tempCourse1 = new CourseEntity();
    CourseEntity tempCourse2 = new CourseEntity();
    CourseEntity tempCourse3 = new CourseEntity();

    AssessmentEntity tempAssessment1 = new AssessmentEntity();
    AssessmentEntity tempAssessment2 = new AssessmentEntity();
    AssessmentEntity tempAssessment3 = new AssessmentEntity();

    NoteEntity tempNote1 = new NoteEntity();
    NoteEntity tempNote2 = new NoteEntity();
    NoteEntity tempNote3 = new NoteEntity();

    AppDatabase db;

    public void populate (Context context) {
        db = AppDatabase.getInstance(context);
        try {
            insertTerms();
            insertCourses();
            insertAssessments();
            insertNotes();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Populate DB Failed");
        }
    }

    private void insertTerms() {
        Calendar start;
        Calendar end;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -2);
        end.add(Calendar.MONTH, 1);
        tempTerm1.setTermTitle("Fall 2020");
        tempTerm1.setTermStartDate(start.getTime());
        tempTerm1.setTermEndDate(end.getTime());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 2);
        end.add(Calendar.MONTH, 5);
        tempTerm2.setTermTitle("Spring 2021");
        tempTerm2.setTermStartDate(start.getTime());
        tempTerm2.setTermEndDate(end.getTime());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 9);
        tempTerm3.setTermTitle("Summer 2021");
        tempTerm3.setTermStartDate(start.getTime());
        tempTerm3.setTermEndDate(end.getTime());

        db.termDao().insertAllTerms(tempTerm1, tempTerm2, tempTerm3);
    }

    private void insertCourses() {
        Calendar start;
        Calendar end;
        List<TermEntity> termEntityList = db.termDao().getTermList();
        if (termEntityList == null) return;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -2);
        end.add(Calendar.MONTH, -1);
        tempCourse1.setCourseTitle("Software 1");
        tempCourse1.setCourseStart(start.getTime());
        tempCourse1.setCourseEnd(end.getTime());
        tempCourse1.setEmailAddress("abc@123.net");
        tempCourse1.setMentorName("Roger Federer");
        tempCourse1.setCourseStatus("Pending");
        tempCourse1.setPhoneNumber("123-123-1234");
        tempCourse1.setTermIdFk(termEntityList.get(0).getTermId());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        //end.add(Calendar.MONTH, 1);
        tempCourse2.setCourseTitle("Software 2");
        tempCourse2.setCourseStart(start.getTime());
        tempCourse2.setCourseEnd(end.getTime());
        tempCourse2.setEmailAddress("123@abc.net");
        tempCourse2.setMentorName("Rafa Nadal");
        tempCourse2.setCourseStatus("Completed");
        tempCourse2.setPhoneNumber("123-123-1234");
        tempCourse2.setTermIdFk(termEntityList.get(0).getTermId());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        //start.add(Calendar.MONTH, -2);
        end.add(Calendar.MONTH, -1);
        tempCourse3.setCourseTitle("Software 3");
        tempCourse3.setCourseStart(start.getTime());
        tempCourse3.setCourseEnd(end.getTime());
        tempCourse3.setEmailAddress("def@456.net");
        tempCourse3.setMentorName("Andy Murray");
        tempCourse3.setCourseStatus("Dropped");
        tempCourse3.setPhoneNumber("123-123-1234");
        tempCourse3.setTermIdFk(termEntityList.get(0).getTermId());

        db.courseDao().insertAllCourses(tempCourse1, tempCourse2, tempCourse3);
    }

    private void insertNotes() {

        List<TermEntity> termEntityList = db.termDao().getTermList();
        List<CourseEntity> courseEntityList = db.courseDao().getCourseList(termEntityList.get(0).getTermId());

        if (courseEntityList == null) return;

        tempNote1.setNoteTitle("Software 1 NOTE");
        tempNote1.setNoteContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        tempNote1.setCourseIdFk(courseEntityList.get(0).getCourseId());

        db.noteDao().insertAllNotes(tempNote1);
    }

    private void insertAssessments() {
        Calendar start;
        Calendar end;
        List<TermEntity> termEntityList = db.termDao().getTermList();
        List<CourseEntity> courseEntityList = db.courseDao().getCourseList(termEntityList.get(0).getTermId());
        if (courseEntityList == null) return;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -2);
        end.add(Calendar.MONTH, -1);
        tempAssessment1.setAssessmentTitle("Software Assess 1");
        tempAssessment1.setAssessmentDueDate(start.getTime());
        tempAssessment1.setAssessmentType("Objective");
        tempAssessment1.setCourseIdFk(courseEntityList.get(0).getCourseId());
        tempAssessment1.setAssessmentStatus("Pending");


        db.assessmentDao().insertAllAssessment(tempAssessment1);
    }
}
