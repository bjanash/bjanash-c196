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
import com.example.bjanash_c196.database.TermEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_course_view extends AppCompatActivity {
    TextView CourseTitle;
    TextView CourseEnd;
    TextView CourseStart;
    TextView CourseStatus;
    TextView MentorName;
    TextView PhoneNumber;
    TextView EmailAddress;
    Intent intent;
    int termId;
    int courseId;
    AppDatabase db;
    SimpleDateFormat formatter;
    CourseEntity selectedCourse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Edit Course");
        termId = intent.getIntExtra("termId", -1);
        courseId = intent.getIntExtra("courseId", -1);
       //System.out.println(termId);
        selectedCourse = db.courseDao().getCourse(termId,courseId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        CourseTitle = findViewById(R.id.CourseTitle);
        CourseStart = findViewById(R.id.CourseStart);
        CourseEnd = findViewById(R.id.CourseEnd);
        CourseStatus = findViewById(R.id.CourseStatus);
        MentorName = findViewById(R.id.MentorName);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        EmailAddress = findViewById(R.id.EmailAddress);

        updateViews();

        Button saveCourseButton = findViewById(R.id.saveCourseButton);
        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String courseTitle = CourseTitle.getText().toString();
                    String courseStartDate = CourseStart.getText().toString();
                    String courseEndDate = CourseEnd.getText().toString();
                    Date formattedStartDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(courseStartDate);
                    Date formattedEndDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(courseEndDate);
                    String courseStatus = CourseStatus.getText().toString();
                    String mentorName = MentorName.getText().toString();
                    String phoneNumber = PhoneNumber.getText().toString();
                    String emailAddress = EmailAddress.getText().toString();

                    CourseEntity updatedCourse = db.courseDao().getCourse(termId, courseId);
                    updatedCourse.setCourseTitle(courseTitle);
                    updatedCourse.setCourseStart(formattedStartDate);
                    updatedCourse.setCourseEnd(formattedEndDate);
                    updatedCourse.setCourseStatus(courseStatus);
                    updatedCourse.setMentorName(mentorName);
                    updatedCourse.setPhoneNumber(phoneNumber);
                    updatedCourse.setEmailAddress(emailAddress);
                    db.courseDao().updateCourse(updatedCourse);
                    Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                    intent.putExtra("termId", termId);
                    intent.putExtra("courseId", courseId);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button deleteCourseButton = findViewById(R.id.deleteCourseButton);
        deleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseEntity updatedCourse = db.courseDao().getCourse(termId, courseId);
                db.courseDao().deleteCourse(updatedCourse);
                Intent intent = new Intent(getApplicationContext(), detailed_term_view.class);
                intent.putExtra("termId", termId);
                startActivity(intent);

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateViews() {
        if(selectedCourse != null){
            Date startDate = selectedCourse.getCourseStart();
            Date endDate = selectedCourse.getCourseEnd();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            CourseStart.setText(tempStart);
            CourseEnd.setText(tempEnd);
            CourseTitle.setText(selectedCourse.getCourseTitle());
            CourseStatus.setText(selectedCourse.getCourseStatus());
            MentorName.setText(selectedCourse.getMentorName());
            PhoneNumber.setText(selectedCourse.getPhoneNumber());
            EmailAddress.setText(selectedCourse.getEmailAddress());
        } else {
            selectedCourse = new CourseEntity();
        }
    }
}
