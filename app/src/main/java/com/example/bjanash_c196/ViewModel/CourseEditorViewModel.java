package com.example.bjanash_c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.TermEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CourseEditorViewModel extends AndroidViewModel {
    public MutableLiveData<CourseEntity> mLiveCourse = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CourseEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(String courseId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                CourseEntity course = mRepository.getCourseById(courseId);
                mLiveCourse.postValue(course);
            }
        });
    }
}
