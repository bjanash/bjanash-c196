package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.NoteEntity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
        System.out.println("Get NoteID " + noteId);
        System.out.println("Get courseID " + courseId);
        selectedNote = db.noteDao().getNote(noteId, courseId);

        NoteTitle = findViewById(R.id.NoteTitle);
        NoteContent = findViewById(R.id.NoteContent);

        updateViews();

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
           // addSampleNotesData();
            return true;
        } else if(id == R.id.action_delete_all){
           // deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
