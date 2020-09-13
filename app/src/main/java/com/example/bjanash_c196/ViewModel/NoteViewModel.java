package com.example.bjanash_c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bjanash_c196.database.AppRepository;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.utilities.SampleData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotes;
    private AppRepository mRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }
    public void addSampleNotesData() { mRepository.addSampleNotesData(); }
    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }
}
