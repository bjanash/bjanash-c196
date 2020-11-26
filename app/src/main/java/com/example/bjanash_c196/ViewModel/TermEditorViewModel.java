package com.example.bjanash_c196.ViewModel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.TermEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TermEditorViewModel extends AndroidViewModel {
    public MutableLiveData<TermEntity> mLiveTerm = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public TermEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(String termId, String termStartDate, String termEndDate) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TermEntity term = mRepository.getTermById(termId, termStartDate, termEndDate );
                mLiveTerm.postValue(term);
            }
        });
    }

    public void saveTerm(String termTitle, String termStartDate, String termEndDate) {
        TermEntity term = mLiveTerm.getValue();

        if(term == null) {
            if(TextUtils.isEmpty(termTitle.trim())) {
                return;
            }
            term = new TermEntity(termTitle.trim(), termStartDate.trim(), termEndDate.trim());
        } else {
            term.setTermTitle(termTitle.trim());
            term.setTermStartDate(termStartDate.trim());
            term.setTermEndDate(termEndDate.trim());
        }
        mRepository.insertTerm(term);
    }

    public void deleteTerm() {
        mRepository.deleteTerm(mLiveTerm.getValue());

    }
}
