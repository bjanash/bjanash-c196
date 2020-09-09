package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.ui.AssessmentsAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class list_of_assessments extends AppCompatActivity {

    @BindView(R.id.recyclerViewAssessments)
    RecyclerView massessmentsRecyclerView;

    private List<AssessmentEntity> assessmentsData = new ArrayList<>();

    private AssessmentsAdapter mAssessmentAdapter;
    private AssessmentViewModel mAssessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_assessments);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //sample data for assessments
        assessmentsData.addAll(mAssessmentViewModel.mAssessments);
        for(AssessmentEntity assessment : assessmentsData) {
            Log.i("assessmentLog", assessment.toString());

        }
        initAssessmentRecyclerView();
        initAssessmentViewModel();

    }

    private void initAssessmentViewModel() {
        mAssessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
    }

    private void initAssessmentRecyclerView(){
        massessmentsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        massessmentsRecyclerView.setLayoutManager(layoutManager);

        mAssessmentAdapter = new AssessmentsAdapter(assessmentsData, this);
        massessmentsRecyclerView.setAdapter(mAssessmentAdapter);
    }


}
