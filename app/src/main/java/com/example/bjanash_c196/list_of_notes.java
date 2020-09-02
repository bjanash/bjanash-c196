package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.ui.NotesAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //sample data for notes
        notesData.addAll(SampleData.getNotes());
        for(NoteEntity note : notesData) {
            Log.i("noteLog", note.toString());

        }
        initTermsRecyclerView();

    }

    private void initTermsRecyclerView(){
        mnotesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mnotesRecyclerView.setLayoutManager(layoutManager);

        mNoteAdapter = new NotesAdapter(notesData, this);
        mnotesRecyclerView.setAdapter(mNoteAdapter);
    }

}
