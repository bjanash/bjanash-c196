package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.ui.AssessmentsAdapter;
import com.example.bjanash_c196.ui.TermsAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
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
        initAssessmentRecyclerView();
        initAssessmentViewModel();

    }

    private void initAssessmentRecyclerView(){
        massessmentsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        massessmentsRecyclerView.setLayoutManager(layoutManager);

    }

    private void initAssessmentViewModel() {
        final Observer<List<AssessmentEntity>> assessmentsObserver = new Observer<List<AssessmentEntity>>() {
            @Override
            public void onChanged(List<AssessmentEntity> assessmentEntities) {
                assessmentsData.clear();
                assessmentsData.addAll(assessmentEntities);

                if(mAssessmentAdapter == null){
                    mAssessmentAdapter = new AssessmentsAdapter(assessmentsData, list_of_assessments.this);
                    massessmentsRecyclerView.setAdapter(mAssessmentAdapter);
                } else{
                    mAssessmentAdapter.notifyDataSetChanged();
                }
            }
        };
        mAssessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
        mAssessmentViewModel.mAssessments.observe(this,assessmentsObserver);
    }


    private void addSampleAssessmentsData() {
        mAssessmentViewModel.addSampleAssessmentsData();
    }
    private void deleteAllAssessments() {
        mAssessmentViewModel.deleteAllAssessments();
    }


}
