package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.NoteViewModel;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.ui.NotesAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class list_of_notes extends AppCompatActivity {

    @BindView(R.id.recyclerViewNotes)
    RecyclerView mnotesRecyclerView;

    private List<NoteEntity> notesData = new ArrayList<>();

    private NotesAdapter mNoteAdapter;
    private NoteViewModel mNoteViewModel;

    @OnClick(R.id.fab_addNote)
    void fabClickHandler() {
        Intent intent = new Intent(this, note_editor.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        //initNotesRecyclerView();
        //initNoteViewModel();

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


        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        mNoteViewModel.mNotes.observe(this,notesObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        //mNoteViewModel.deleteAllNotes();
    }

    //private void addSampleNotesData() {
       // mNoteViewModel.addSampleNotesData();
    //}



}
