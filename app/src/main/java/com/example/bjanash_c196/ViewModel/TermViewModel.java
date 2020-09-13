package com.example.bjanash_c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.utilities.SampleData;


import java.util.List;

public class TermViewModel extends AndroidViewModel {

    public LiveData<List<TermEntity>> mTerms;
    private AppRepository mRepository;

    public TermViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mTerms = mRepository.mTerms;
    }

    public void addSampleTermsData() { mRepository.addSampleTermsData(); }

    public void deleteAllTerms() {
        mRepository.deleteAllTerms();
    }
}
