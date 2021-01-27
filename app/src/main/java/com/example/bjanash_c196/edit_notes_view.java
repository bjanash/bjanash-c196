package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_notes_view extends AppCompatActivity {
    TextView NoteTitle;
    TextView NoteContent;

    Intent intent;

    int courseId;
    int noteId;

    AppDatabase db;
    SimpleDateFormat formatter;
    NoteEntity selectedNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Edit Note");
        courseId = intent.getIntExtra("courseId", -1);
        noteId = intent.getIntExtra("noteId", -1);
       //System.out.println(termId);
        selectedNote = db.noteDao().getNote(courseId, noteId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        NoteTitle = findViewById(R.id.NoteTitle);
        NoteContent = findViewById(R.id.NoteContent);


        updateViews();

        Button saveNoteButton = findViewById(R.id.saveNoteButton);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = NoteTitle.getText().toString();
                String noteContent = NoteContent.getText().toString();

                NoteEntity updatedNote = db.noteDao().getNote(courseId, noteId);
                updatedNote.setNoteTitle(noteTitle);
                updatedNote.setNoteContent(noteContent);

                db.noteDao().updateNote(updatedNote);
                Intent intent = new Intent(getApplicationContext(), detailed_note_view.class);
                intent.putExtra("courseId", courseId);
                intent.putExtra("noteId", noteId);
                startActivity(intent);
            }
        });

        Button deleteNoteButton = findViewById(R.id.deleteNoteButton);
        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteEntity updatedNote = db.noteDao().getNote(courseId, noteId);
                db.noteDao().deleteNote(updatedNote);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateViews() {
        if(selectedNote != null){
            NoteTitle.setText(selectedNote.getNoteTitle());
            NoteContent.setText(selectedNote.getNoteContent());
        } else {
            selectedNote = new NoteEntity();
        }
    }
}
