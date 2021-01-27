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
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.MentorEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_mentor_view extends AppCompatActivity {
    TextView MentorName;
    TextView PhoneNumber;
    TextView EmailAddress;

    Intent intent;
    int mentorId;
    int courseId;
    AppDatabase db;
    MentorEntity selectedMentor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_editor);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Edit Mentor");
        mentorId = intent.getIntExtra("mentorId", -1);
        courseId = intent.getIntExtra("courseId", -1);
       //System.out.println(termId);
        selectedMentor = db.mentorDao().getMentor(mentorId,courseId);

        MentorName = findViewById(R.id.mentorName);
        PhoneNumber = findViewById(R.id.phoneNumber);
        EmailAddress = findViewById(R.id.emailAddress);

        updateViews();

        Button saveMentorButton = findViewById(R.id.saveMentor);
        saveMentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mentorName = MentorName.getText().toString();
                String phoneNumber = PhoneNumber.getText().toString();
                String emailAddress= EmailAddress.getText().toString();

                MentorEntity updatedMentor = db.mentorDao().getMentor(mentorId, courseId);
                updatedMentor.setMentorName(mentorName);
                updatedMentor.setPhoneNumber(phoneNumber);
                updatedMentor.setEmailAddress(emailAddress);
                db.mentorDao().updateMentor(updatedMentor);
                Intent intent = new Intent(getApplicationContext(), detailed_mentor_view.class);
                intent.putExtra("mentorId", mentorId);
                intent.putExtra("courseId", courseId);
                startActivity(intent);
            }
        });

        Button deleteMentorButton = findViewById(R.id.deleteMentor);
        deleteMentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MentorEntity updatedMentor = db.mentorDao().getMentor(mentorId, courseId);
                db.mentorDao().deleteMentor(updatedMentor);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateViews() {
        if(selectedMentor != null){
            MentorName.setText(selectedMentor.getMentorName());
            PhoneNumber.setText(selectedMentor.getPhoneNumber());
            EmailAddress.setText(selectedMentor.getEmailAddress());
        } else {
            selectedMentor = new MentorEntity();
        }
    }
}
