package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.MentorEntity;
import com.example.bjanash_c196.database.NoteEntity;

public class detailed_mentor_view extends AppCompatActivity {

    TextView MentorName;
    TextView PhoneNumber;
    TextView EmailAddress;

    AppDatabase db;

    int mentorId;
    int courseId;
    Intent intent;
    MentorEntity selectedMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_mentor_view);

        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Mentor Detail View");
        mentorId = intent.getIntExtra("mentorId", -1);
        courseId = intent.getIntExtra("courseId", -1);
        System.out.println("Get MentorID " + mentorId);
        System.out.println("Get courseID " + courseId);
        selectedMentor = db.mentorDao().getMentor(courseId, mentorId);

        MentorName = findViewById(R.id.mentorName);
        PhoneNumber = findViewById(R.id.phoneNumber);
        EmailAddress = findViewById(R.id.emailAddress);

        updateViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button backToCourseMentor = findViewById(R.id.backToCourseMentor);
        backToCourseMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CourseEntity updatedCourse = db.courseDao().getCourse(termId, courseId);
                ///db.courseDao().deleteCourse(updatedCourse);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mentor_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_edit_mentor) {
            Intent intent = new Intent(this, edit_mentor_view.class);
            intent.putExtra("mentorId", mentorId);
            intent.putExtra("courseId", courseId);
            System.out.println(mentorId);
            this.startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
