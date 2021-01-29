package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_assessment_view extends AppCompatActivity {
    TextView AssessmentTitle;
    TextView DueDate;
    TextView AssessmentType;
    TextView AssessmentStatus;
    Intent intent;
    int assessmentId;
    int courseId;
    AppDatabase db;
    SimpleDateFormat formatter;
    AssessmentEntity selectedAssessment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_editor);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Edit Assessment");
        assessmentId = intent.getIntExtra("assessmentId", -1);
        courseId = intent.getIntExtra("courseId", -1);
       //System.out.println(termId);
        selectedAssessment = db.assessmentDao().getAssessment(courseId, assessmentId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        AssessmentTitle = findViewById(R.id.AssessmentTitle);
        DueDate = findViewById(R.id.DueDate);
        AssessmentType = findViewById(R.id.AssessmentType);
        AssessmentStatus = findViewById(R.id.assessmentStatus);

        updateViews();

        Button saveAssessmentButton = findViewById(R.id.saveAssessment);
        saveAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String assessmentTitle = AssessmentTitle.getText().toString();
                    String assessmentDueDate = DueDate.getText().toString();
                    String assessmentType = AssessmentType.getText().toString();
                    String assessmentStatus = AssessmentType.getText().toString();

                    Date formattedStartDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(assessmentDueDate);

                    AssessmentEntity updatedAssessment = db.assessmentDao().getAssessment(courseId, assessmentId);
                    updatedAssessment.setAssessmentTitle(assessmentTitle);
                    updatedAssessment.setAssessmentDueDate(formattedStartDate);
                    updatedAssessment.setAssessmentType(assessmentType);
                    updatedAssessment.setAssessmentStatus(assessmentStatus);

                    db.assessmentDao().updateAssessment(updatedAssessment);
                    Intent intent = new Intent(getApplicationContext(), detailed_assessment_view.class);
                    intent.putExtra("assessmentId", assessmentId);
                    intent.putExtra("courseId", courseId);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button deleteAssessmentButton = findViewById(R.id.deleteAssessment);
        deleteAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssessmentEntity updatedAssessment = db.assessmentDao().getAssessment(courseId, assessmentId);
                db.assessmentDao().deleteAssessment(updatedAssessment);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateViews() {
        if(selectedAssessment != null){
            Date startDate = selectedAssessment.getAssessmentDueDate();
            String tempStart = formatter.format(startDate);
            DueDate.setText(tempStart);
            AssessmentTitle.setText(selectedAssessment.getAssessmentTitle());
            AssessmentType.setText(selectedAssessment.getAssessmentType());
            AssessmentStatus.setText(selectedAssessment.getAssessmentStatus());
        } else {
            selectedAssessment = new AssessmentEntity();
        }
    }

}
