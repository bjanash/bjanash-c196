package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.ui.TermsAdapter;
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
import butterknife.OnClick;

public class list_of_terms extends AppCompatActivity {

    @BindView(R.id.terms_recyclerView)
    RecyclerView mtermsRecyclerView;

    @OnClick(R.id.fab_addTerm)
    void fabClickHandler() {
        Intent intent = new Intent(this, term_editor.class);
        startActivity(intent);
    }

    private List<TermEntity> termsData = new ArrayList<>();
    private List<CourseEntity> coursesData = new ArrayList<>();
    private List<NoteEntity> notesData = new ArrayList<>();
    private List<AssessmentEntity> assessmentsData = new ArrayList<>();


    private TermsAdapter mTermAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_terms);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);



        //sample data for terms
        termsData.addAll(SampleData.getTerms());
        for(TermEntity term : termsData) {
            Log.i("termLog", term.toString());
        }
        //sample data for courses
        coursesData.addAll(SampleData.getCourses());
        for(CourseEntity course : coursesData) {
            Log.i("courseLog", course.toString());

        }

        //sample data for notes
        notesData.addAll(SampleData.getNotes());
        for(NoteEntity note : notesData) {
            Log.i("noteLog", note.toString());

        }

        //sample data for assessments
        assessmentsData.addAll(SampleData.getAssessments());
        for(AssessmentEntity assessment : assessmentsData) {
            Log.i("assessmentLog", assessment.toString());

        }
        initTermsRecyclerView();
    }

    private void initTermsRecyclerView(){
        mtermsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mtermsRecyclerView.setLayoutManager(layoutManager);

        mTermAdapter = new TermsAdapter(termsData, this);
        mtermsRecyclerView.setAdapter(mTermAdapter);
    }

}
