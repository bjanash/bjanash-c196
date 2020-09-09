package com.example.bjanash_c196.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.bjanash_c196.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static  AppRepository ourInstance;
    public List<AssessmentEntity> mAssessments;
    public List<CourseEntity> mCourses;
    public LiveData<List<TermEntity>> mTerms ;
    public List<NoteEntity> mNotes;

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
        mTerms = getAllTerms();
        mAssessments = SampleData.getAssessments();
        mCourses = SampleData.getCourses();
        mNotes = SampleData.getNotes();

    }



   public void addSampleTermsData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().insertAll(SampleData.getTerms());
            }
        });
    }

    private LiveData<List<TermEntity>> getAllTerms(){
        return mDb.termDao().getAllTerms();
    }
}
