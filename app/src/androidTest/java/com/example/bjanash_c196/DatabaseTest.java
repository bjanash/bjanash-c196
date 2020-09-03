package com.example.bjanash_c196;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentDao;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseDao;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteDao;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermDao;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.utilities.SampleData;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private AssessmentDao mAssessmentDao;
    private CourseDao mCourseDao;
    private NoteDao mNoteDao;
    private TermDao mTermDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mAssessmentDao = mDb.assessmentDao();
        mCourseDao = mDb.courseDao();
        mNoteDao = mDb.noteDao();
        mTermDao = mDb.termDao();

        Log.i(TAG, "CreateDb");

    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "Close Db");
    }

    @Test
    public void compareAssessmentStrings() {
        mAssessmentDao.insertAll(SampleData.getAssessments());
        AssessmentEntity original = SampleData.getAssessments().get(0);
        AssessmentEntity fromDb = mAssessmentDao.getAssessmentbyTitle("Sample Assessment 1");
        Log.i(TAG, "compareAssessmentStrings: " + original + "and" + fromDb);
        assertEquals(original.getAssessmentTitle(), fromDb.getAssessmentTitle());
    }

    @Test
    public void compareCourseStrings() {
        mCourseDao.insertAll(SampleData.getCourses());
        CourseEntity original = SampleData.getCourses().get(0);
        CourseEntity fromDb = mCourseDao.getCoursebyTitle("Sample Course 1");
        Log.i(TAG, "compareCourseStrings: " + original + "and" + fromDb);
        assertEquals(original.getCourseTitle(), fromDb.getCourseTitle());
    }

    @Test
    public void compareTermStrings() {
        mTermDao.insertAll(SampleData.getTerms());
        TermEntity original = SampleData.getTerms().get(0);
        TermEntity fromDb = mTermDao.getTermbyTitle("Sample Term 1");
        Log.i(TAG, "compareTermStrings: " + original + "and" + fromDb);
        assertEquals(original.getTermTitle(), fromDb.getTermTitle());
    }

    @Test
    public void compareNoteStrings() {
        mNoteDao.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mNoteDao.getNotebyTitle("Sample Note 1");
        Log.i(TAG, "compareNoteStrings: " + original + "and" + fromDb);
        assertEquals(original.getNoteTitle(), fromDb.getNoteTitle());
    }


}
