package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.NoteViewModel;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.ui.CoursesAdapter;
import com.example.bjanash_c196.ui.NotesAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class list_of_notes extends AppCompatActivity {

    @BindView(R.id.recyclerViewNotes)
    RecyclerView mnotesRecyclerView;

    private List<NoteEntity> notesData = new ArrayList<>();

    private NotesAdapter mNoteAdapter;
    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initNotesRecyclerView();
        initNoteViewModel();

    }

    private void initNotesRecyclerView(){
        mnotesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mnotesRecyclerView.setLayoutManager(layoutManager);

    }

    private void initNoteViewModel() {
        final Observer<List<NoteEntity>> notesObserver = new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                notesData.clear();
                notesData.addAll(noteEntities);

                if (mNoteAdapter == null) {
                    mNoteAdapter = new NotesAdapter(notesData, list_of_notes.this);
                    mnotesRecyclerView.setAdapter(mNoteAdapter);
                } else {
                    mNoteAdapter.notifyDataSetChanged();
                }
            }
        };
        mNoteAdapter = new NotesAdapter(notesData, this);
        mnotesRecyclerView.setAdapter(mNoteAdapter);
    }

    private void addSampleNotessData() {
        mNoteViewModel.addSampleNotesData();
    }
    private void deleteAllNotes() {
        mNoteViewModel.deleteAllNotes();
    }



}
