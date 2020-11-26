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

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.ASSESSMENT_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.COURSE_TITLE_ID;

public class assessment_editor extends AppCompatActivity {

    @BindView(R.id.AssessmentTitle)
    TextView mAssessmentTitle;

    private AssessmentEditorViewModel mAssessmentViewModel;
    private boolean mNewAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initAssessmentViewModel();

    }

    private void initAssessmentViewModel() {
        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentEditorViewModel.class);

        mAssessmentViewModel.mLiveAssessment.observe(this, new Observer<AssessmentEntity>() {
            @Override
            public void onChanged(AssessmentEntity assessmentEntity) {
                mAssessmentTitle.setText(assessmentEntity.getAssessmentTitle());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Assessment");
            mNewAssessment = true;
        } else {
            setTitle("Edit Assessment");
            String assessmentId = (String) extras.get(ASSESSMENT_TITLE_ID);
            mAssessmentViewModel.loadData(assessmentId);
        }

    }

}
