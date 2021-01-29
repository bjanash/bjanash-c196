package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.NoteEntity;

public class detailed_note_view extends AppCompatActivity {

    TextView NoteTitle;
    TextView NoteContent;

    AppDatabase db;

    int noteId;
    int courseId;
    Intent intent;
    NoteEntity selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_note_view);

        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Note Detail View");
        noteId = intent.getIntExtra("noteId", -1);
        courseId = intent.getIntExtra("courseId", -1);
        System.out.println("Get courseID " + courseId);
        System.out.println("Get NoteID " + noteId);
        selectedNote = db.noteDao().getNote(courseId, noteId);

        NoteTitle = findViewById(R.id.NoteTitle);
        NoteContent = findViewById(R.id.NoteContent);

        updateViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button backToCourseNote = findViewById(R.id.backToCourseNote);
        backToCourseNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CourseEntity updatedCourse = db.courseDao().getCourse(termId, courseId);
                ///db.courseDao().deleteCourse(updatedCourse);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });

        Button shareNoteButton = findViewById(R.id.shareNoteButton);
        shareNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                //sendIntent.putExtra(Intent.EXTRA_TEXT, "Note Title: ");
                sendIntent.putExtra(Intent.EXTRA_TEXT, NoteContent.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return;

            }
        });

    }

    private void updateViews() {
        if(selectedNote != null){
            NoteTitle.setText(selectedNote.getNoteTitle());
            NoteContent.setText(selectedNote.getNoteContent());
        } else {
            selectedNote = new NoteEntity();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_edit_note) {
            Intent intent = new Intent(this, edit_notes_view.class);
            intent.putExtra("courseId", courseId);
            intent.putExtra("noteId", noteId);
            this.startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
