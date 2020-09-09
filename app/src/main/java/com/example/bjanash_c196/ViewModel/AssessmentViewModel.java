package com.example.bjanash_c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.utilities.SampleData;

import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {

    public List<AssessmentEntity> mAssessments;
    private AppRepository mRepository;

    public AssessmentViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mAssessments = mRepository.mAssessments;


    }
}
