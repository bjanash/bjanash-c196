package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.NoteEditorViewModel;
import com.example.bjanash_c196.ViewModel.NoteViewModel;
import com.example.bjanash_c196.ViewModel.TermEditorViewModel;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.NOTE_CONTENT_ID;
import static com.example.bjanash_c196.utilities.Constants.NOTE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;

public class note_editor extends AppCompatActivity {

    @BindView(R.id.NoteTitle)
    TextView mNoteTitle;

    @BindView(R.id.NoteContent)
    TextView mNoteContent;

    private NoteEditorViewModel mNoteEditorViewModel;
    private NoteViewModel mNoteViewModel;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_check_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initNoteViewModel();

    }
    private void initNoteViewModel() {
        mNoteEditorViewModel = new ViewModelProvider(this).get(NoteEditorViewModel.class);

        mNoteEditorViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                mNoteTitle.setText(noteEntity.getNoteTitle());
                mNoteContent.setText(noteEntity.getNoteContent());
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Note");
            mNewNote = true;
        } else {
            setTitle("Edit Note");
            String noteId = (String) extras.get(NOTE_TITLE_ID);
            String noteContent = (String) extras.get(NOTE_CONTENT_ID);
          //  mNoteEditorViewModel.loadData(noteId, noteContent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNewNote){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.note_editor_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
           // mNoteEditorViewModel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {

        //mNoteEditorViewModel.saveNote(mNoteTitle.getText().toString(), mNoteContent.getText().toString());
        finish();
    }

}
