package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentEditorViewModel;
import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.ViewModel.TermEditorViewModel;
import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.ui.AssessmentsAdapter;
import com.example.bjanash_c196.ui.CoursesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.bjanash_c196.utilities.Constants.ASSESSMENT_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.END_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.START_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;

public class detailed_assessment_view extends AppCompatActivity {


    TextView AssessmentTitle;
    TextView AssessmentDueDate;
    TextView AssessmentType;

    AppDatabase db;

    int assessmentId;
    int courseId;
    Intent intent;
    SimpleDateFormat formatter;
    AssessmentEntity selectedAssessment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_assessment_view);

        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Assessment Detail View");
        assessmentId = intent.getIntExtra("assessmentId", -1);
        courseId = intent.getIntExtra("courseId", -1);
        System.out.println("Get AssessmentID " + assessmentId);
        System.out.println("Get courseID " + courseId);
        selectedAssessment = db.assessmentDao().getAssessment(assessmentId, courseId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        AssessmentTitle = findViewById(R.id.AssessmentTitle);
        AssessmentDueDate = findViewById(R.id.DueDate);
        AssessmentType = findViewById(R.id.AssessmentType);

        updateViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    private void updateViews() {
        if(selectedAssessment != null){
            Date dueDate = selectedAssessment.getAssessmentDueDate();
            String tempDueDate = formatter.format(dueDate);
            AssessmentTitle.setText(selectedAssessment.getAssessmentTitle());
            AssessmentDueDate.setText(tempDueDate);
            AssessmentType.setText(selectedAssessment.getAssessmentType());
        } else {
            selectedAssessment = new AssessmentEntity();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessment_detail_menu, menu);
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
         //   addSampleAssessmentsData();
            return true;
        } else if(id == R.id.action_delete_all){
           // deleteAllAssessments();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
