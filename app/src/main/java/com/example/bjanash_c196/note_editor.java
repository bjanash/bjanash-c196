package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.NoteEditorViewModel;
import com.example.bjanash_c196.ViewModel.TermEditorViewModel;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.NOTE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;

public class note_editor extends AppCompatActivity {

    @BindView(R.id.NoteTitle)
    TextView mNoteTitle;

    private NoteEditorViewModel mNoteViewModel;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initNoteViewModel();

    }
    private void initNoteViewModel() {
        mNoteViewModel = new ViewModelProvider(this).get(NoteEditorViewModel.class);

        mNoteViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                mNoteTitle.setText(noteEntity.getNoteTitle());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Note");
            mNewNote = true;
        } else {
            setTitle("Edit Note");
            String noteId = (String) extras.get(NOTE_TITLE_ID);
            mNoteViewModel.loadData(noteId);
        }

    }

}
