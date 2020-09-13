package com.example.bjanash_c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.utilities.SampleData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    public LiveData<List<CourseEntity>> mCourses;
    private AppRepository mRepository;

    public CourseViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mCourses = mRepository.mCourses;
    }
    public void addSampleCoursesData() { mRepository.addSampleCoursesData(); }
    public void deleteAllCourses() {
        mRepository.deleteAllCourses();
    }
}
