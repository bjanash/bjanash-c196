package com.example.bjanash_c196;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.model.AssessmentEntity;
import com.example.bjanash_c196.model.CourseEntity;
import com.example.bjanash_c196.model.NoteEntity;
import com.example.bjanash_c196.model.TermEntity;
import com.example.bjanash_c196.ui.TermsAdapter;
import com.example.bjanash_c196.utilities.SampleData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TermEntity> termsData = new ArrayList<>();
    private List<CourseEntity> coursesData = new ArrayList<>();
    private List<NoteEntity> notesData = new ArrayList<>();
    private List<AssessmentEntity> assessmentsData = new ArrayList<>();



    //Button list_of_terms_button = (Button) findViewById(R.id.list_of_terms_button);
    //Button list_of_courses_button = (Button) findViewById(R.id.list_of_courses_button);
    //Button list_of_assessments_button = (Button) findViewById(R.id.list_of_assessments_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Go to List of Terms Screen
        Button terms = (Button) findViewById(R.id.list_of_terms_button);
        terms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), list_of_terms.class);
                startActivityForResult(myIntent, 0);
            }
        });

        /*Go to List of Courses Screen
        Button courses = (Button) findViewById(R.id.list_of_courses_button);
        terms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), list_of_courses.class);
                startActivityForResult(myIntent, 0);
            }
        });

        //Go to List of Assessments Screen
        Button assessments = (Button) findViewById(R.id.list_of_assessments_button);
        terms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), list_of_assessments.class);
                startActivityForResult(myIntent, 0);
            }
        }); */

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
