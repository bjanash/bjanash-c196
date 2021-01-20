package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.ui.AssessmentsAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class list_of_assessments extends AppCompatActivity {

    @BindView(R.id.recyclerViewAssessments)
    RecyclerView massessmentsRecyclerView;
    private AssessmentViewModel mAssessmentViewModel;

    private List<AssessmentEntity> assessmentsData = new ArrayList<>();
    private AssessmentsAdapter mAssessmentAdapter;

    @OnClick(R.id.fab_addAssessment)
    void fabClickHandler() {
        Intent intent = new Intent(this, assessment_editor.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_assessments);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        //initAssessmentViewModel();
        //initAssessmentRecyclerView();
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
            addSampleAssessmentsData();
            return true;
        } else if(id == R.id.action_delete_all){
          //  deleteAllAssessments();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void addSampleAssessmentsData() {
       // mAssessmentViewModel.addSampleAssessmentsData();
    }
    //private void deleteAllAssessments() {
      //  mAssessmentViewModel.deleteAllAssessments();
    //}


}
