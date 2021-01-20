package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentEditorViewModel;
import com.example.bjanash_c196.ViewModel.CourseEditorViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.ASSESSMENT_DUE_DATE;
import static com.example.bjanash_c196.utilities.Constants.ASSESSMENT_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.ASSESSMENT_TYPE;
import static com.example.bjanash_c196.utilities.Constants.COURSE_TITLE_ID;

public class assessment_editor extends AppCompatActivity {

    @BindView(R.id.AssessmentTitle)
    TextView mAssessmentTitle;

    @BindView(R.id.DueDate)
    TextView mAssessmentDueDate;

    @BindView(R.id.AssessmentType)
    TextView mAssessmentType;

    private AssessmentEditorViewModel mAssessmentViewModel;
    private boolean mNewAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_check_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initAssessmentViewModel();

    }

    private void initAssessmentViewModel() {
        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentEditorViewModel.class);

        mAssessmentViewModel.mLiveAssessment.observe(this, new Observer<AssessmentEntity>() {
            @Override
            public void onChanged(AssessmentEntity assessmentEntity) {
                mAssessmentTitle.setText(assessmentEntity.getAssessmentTitle());
                //mAssessmentDueDate.setText(assessmentEntity.getAssessmentDueDate());
                mAssessmentType.setText(assessmentEntity.getAssessmentType());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Assessment");
            mNewAssessment = true;
        } else {
            setTitle("Edit Assessment");
            String assessmentId = (String) extras.get(ASSESSMENT_TITLE_ID);
            String assessmentDueDate = (String) extras.get(ASSESSMENT_DUE_DATE);
            String assessmentType = (String) extras.get(ASSESSMENT_TYPE);
           // mAssessmentViewModel.loadData(assessmentId, assessmentDueDate, assessmentType);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNewAssessment){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.assessment_editor_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            //mAssessmentViewModel.deleteAssessment();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {

       // mAssessmentViewModel.saveAssessment(mAssessmentTitle.getText().toString(), mAssessmentDueDate.getText().toString(), mAssessmentType.getText().toString());
        finish();
    }

}
