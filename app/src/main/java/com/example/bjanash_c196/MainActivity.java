package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.utilities.PopulateDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG ="Main Activity";
    AppDatabase db;
    TextView coursesPendingTextView;
    TextView coursesCompletedTextView;
    TextView coursesDroppedTextView;
    TextView assessmentPendingTextView;
    TextView assessmentPassedTextView;
    TextView assessmentFailedTextView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getInstance(getApplicationContext());
        //Views//
        coursesPendingTextView = findViewById(R.id.coursesPendingTextView);
        coursesCompletedTextView =  findViewById(R.id.coursesCompletedTextView);
        coursesDroppedTextView =  findViewById(R.id.coursesDroppedTextView);
        assessmentPendingTextView =  findViewById(R.id.assessmentPendingTextView);
        assessmentPassedTextView =  findViewById(R.id.assessmentPassedTextView);
        assessmentFailedTextView =  findViewById(R.id.assessmentFailedTextView);
        
        updateViews();
        
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

        //Populate DB
        ConstraintLayout myLayout = findViewById(R.id.content_main);
        ConstraintSet set = new ConstraintSet();
        Button populateDbButton = new Button(getApplicationContext());
        populateDbButton.setText("Populate Database");
        populateDbButton.setId(R.id.populateDbButton);

        set.constrainHeight(populateDbButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(populateDbButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(populateDbButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);
        set.connect(populateDbButton.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT,8);

        if(myLayout.getParent() != null) {
            ((ViewGroup) myLayout.getParent()).removeView(myLayout);
        }

        myLayout.addView(populateDbButton);
        setContentView(myLayout);
        set.applyTo(myLayout);

        populateDbButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Populate Database Pressed");
                PopulateDatabase populateDatabase = new PopulateDatabase();
                populateDatabase.populate(getApplicationContext());
                updateViews();
            }}

        );

       //Destroy DB
        Button destroyDbButton = new Button(getApplicationContext());
        destroyDbButton.setText("Destroy Database");
        destroyDbButton.setId(R.id.destroyDbButton);

        set.constrainHeight(destroyDbButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(destroyDbButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(destroyDbButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,8);
        set.connect(destroyDbButton.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,8);

        myLayout.addView(destroyDbButton);
        setContentView(myLayout);
        set.applyTo(myLayout);

        destroyDbButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Destroy Database Pressed");
                db.clearAllTables();
                updateViews();
            }}
        );


    }

    private void updateViews() {
        int coursePending = 0;
        int courseCompleted = 0;
        int courseDropped = 0;
        int assessmentPending = 0;
        int assessmentPassed = 0;
        int assessmentFailed = 0;

        try {
            List<TermEntity> termEntityList = db.termDao().getAllTerms();
            List<CourseEntity> courseEntityList = db.courseDao().getAllCourses();
            List<AssessmentEntity> assessmentEntityList = db.assessmentDao().getAllAssessments();

            try {
                for (int i = 0; i < courseEntityList.size(); i++) {
                    if (courseEntityList.get(i).getCourseStatus().contains("Pending"))
                        coursePending++;
                    if (courseEntityList.get(i).getCourseStatus().contains("In-Progress"))
                        coursePending++;
                    if (courseEntityList.get(i).getCourseStatus().contains("Completed"))
                        courseCompleted++;
                    if (courseEntityList.get(i).getCourseStatus().contains("Dropped"))
                        courseDropped++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < assessmentEntityList.size(); i++) {
                if (assessmentEntityList.get(i).getAssessmentStatus().contains("Pending"))
                    assessmentPending++;
                if (assessmentEntityList.get(i).getAssessmentStatus().contains("Passed"))
                    assessmentPassed++;
                if (assessmentEntityList.get(i).getAssessmentStatus().contains("Failed"))
                    assessmentFailed++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        coursesPendingTextView.setText(String.valueOf(coursePending));
        coursesCompletedTextView.setText(String.valueOf(courseCompleted));
        coursesDroppedTextView.setText(String.valueOf(courseDropped));
        assessmentPendingTextView.setText(String.valueOf(assessmentPending));
        assessmentPassedTextView.setText(String.valueOf(assessmentFailed));
        assessmentFailedTextView.setText(String.valueOf(assessmentPassed));

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateViews();
    }
}
