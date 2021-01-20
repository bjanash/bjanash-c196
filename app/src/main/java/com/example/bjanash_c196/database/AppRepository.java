package com.example.bjanash_c196.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static  AppRepository ourInstance;
    public LiveData<List<AssessmentEntity>> mAssessments;
    public LiveData<List<CourseEntity>> mCourses;
    public LiveData<List<TermEntity>> mTerms ;
    public LiveData<List<NoteEntity>> mNotes;

    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
       // mTerms = getAllTerms();
        //mAssessments = getAllAssessments();
        //mCourses = getAllCourses();
        //mNotes = getAllNotes();

    }


   /* public void addSampleTermsData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().insertAll(SampleData.getTerms());
            }
        });
    }

    public void addSampleAssessmentsData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.assessmentDao().insertAll(SampleData.getAssessments());
            }
        });
    }

    public void addSampleNotesData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAll(SampleData.getNotes());
            }
        });
    }

    public void addSampleCoursesData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.courseDao().insertAll(SampleData.getCourses());
            }
        });
    }

    private LiveData<List<TermEntity>> getAllTerms(){
        return mDb.termDao().getAllTerms();
    }
    private LiveData<List<NoteEntity>> getAllNotes(){
        return mDb.noteDao().getAllNotes();
    }
    private LiveData<List<CourseEntity>> getAllCourses(){
        return mDb.courseDao().getAllCourses();
    }
    private LiveData<List<AssessmentEntity>> getAllAssessments(){
        return mDb.assessmentDao().getAllAssessments();
    }

    public void deleteAllTerms() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().deleteAll();
            }
        });
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }

    public void deleteAllAssessments() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.assessmentDao().deleteAll();
            }
        });
    }

    public void deleteAllCourses() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.courseDao().deleteAll();
            }
        });
    }

    public TermEntity getTermById(String termId, String termStartDate, String termEndDate) { return mDb.termDao().getTermbyTitle(termId, termStartDate, termEndDate); }
    public CourseEntity getCourseById(String courseId, String courseStart, String courseEnd, String courseStatus, String mentorName, String phoneNumber, String emailAddress, String termTitle) { return mDb.courseDao().getCoursebyTitle(courseId, courseStart, courseEnd, courseStatus, mentorName, phoneNumber, emailAddress, termTitle); }
    public NoteEntity getNoteById(String noteId, String noteContent) {
        return mDb.noteDao().getNotebyTitle(noteId, noteContent);
    }
    public AssessmentEntity getAssessmentById(String assessmentId, String assessmentDueDate, String assessmentType) { return mDb.assessmentDao().getAssessmentbyTitle(assessmentId, assessmentDueDate, assessmentType); }

    public void insertTerm(final TermEntity term) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().insertTerm(term);
            }
        });
    }

    public void deleteTerm(final TermEntity term) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().deleteTerm(term);
            }
        });
    }

    public void insertCourse(final CourseEntity course) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.courseDao().insertCourse(course);
            }
        });
    }

    public void deleteCourse(final CourseEntity course) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.courseDao().deleteCourse(course);
            }
        });
    }

    public void insertAssessment(final AssessmentEntity assessment) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.assessmentDao().insertAssessment(assessment);
            }
        });
    }

    public void deleteAssessment(final AssessmentEntity assessment) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.assessmentDao().deleteAssessment(assessment);
            }
        });
    }

    public void insertNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertNote(note);
            }
        });
    }

    public void deleteNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteNote(note);
            }
        });
    }

    public CourseEntity getTermTitle(String termTitle) {
        { return mDb.courseDao().getCourseTermTitle(termTitle); }
    }
*/



}
