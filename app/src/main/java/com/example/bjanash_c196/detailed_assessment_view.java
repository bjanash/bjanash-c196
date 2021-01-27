package com.example.bjanash_c196;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.bjanash_c196.AlertReminder.LOG_INFO;

public class detailed_assessment_view extends AppCompatActivity {

    private static final int NOTIFY_ID = 747;
    TextView AssessmentTitle;
    TextView AssessmentDueDate;
    TextView AssessmentType;
    TextView AssessmentStatus;

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
        courseId = intent.getIntExtra("courseId", -1);
        assessmentId = intent.getIntExtra("assessmentId", -1);
        System.out.println("Get AssessmentID " + assessmentId);
        System.out.println("Get courseID " + courseId);
        selectedAssessment = db.assessmentDao().getAssessment(courseId, assessmentId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        AssessmentTitle = findViewById(R.id.AssessmentTitle);
        AssessmentDueDate = findViewById(R.id.DueDate);
        AssessmentType = findViewById(R.id.AssessmentType);
        AssessmentStatus = findViewById(R.id.assessmentStatus);

        updateViews();
        notifyChannel();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button backToCourse = findViewById(R.id.backToCourse);
        backToCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CourseEntity updatedCourse = db.courseDao().getCourse(termId, courseId);
                ///db.courseDao().deleteCourse(updatedCourse);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            }
        });

        Button setAlertAssessmentButton = findViewById(R.id.setAlertAssessmentButton);
        setAlertAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Set Alert (Click)");

                Calendar calDate = Calendar.getInstance();
                calDate.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDateEnd = calDate.getTime();
                try {
                    alertDateEnd = formatter.parse(AssessmentDueDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setAlert(alertDateEnd);
            }
        });



    }

    private void setAlert(Date calDateProvided) {
        Calendar calDateNow = Calendar.getInstance();
        if(calDateProvided.getTime() > calDateNow.getTime().getTime()){
            Log.d(LOG_INFO, formatter.format(calDateProvided) + " notify date");
            Intent sendIntent = new Intent(getApplicationContext(), AlertReminder.class);
            sendIntent.putExtra("my_title", "Important Date");
            sendIntent.putExtra("my_message", "Assessment: " + AssessmentTitle.getText().toString() + " is due\n" + "Date and Time: " + formatter.format(calDateProvided));
            sendIntent.putExtra("notify_id", NOTIFY_ID);
            PendingIntent thisPendIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    NOTIFY_ID,
                    sendIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alertManage = (AlarmManager) getSystemService(ALARM_SERVICE);
            alertManage.set(AlarmManager.RTC, calDateProvided.getTime(), thisPendIntent);
            Toast.makeText(getApplicationContext(), "Alert set for: " + AssessmentTitle.getText().toString(), Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), formatter.format(calDateProvided) + " is a PAST DATE or TIME", Toast.LENGTH_LONG).show();
        }

    }

    private void notifyChannel() {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence dgw_channel_name = "DGW Tracker NAME";
            String dgw_channel_description = "DGW Tracker DESC";
            int dgw_channel_importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifyChannel = new NotificationChannel("dgwChanId", dgw_channel_name,dgw_channel_importance);
            notifyChannel.setDescription(dgw_channel_description);

            NotificationManager notifyManager = getSystemService(NotificationManager.class);
            notifyManager.createNotificationChannel(notifyChannel);
        }
    }

    private void updateViews() {
        if(selectedAssessment != null){
            Date dueDate = selectedAssessment.getAssessmentDueDate();
            String tempDueDate = formatter.format(dueDate);
            AssessmentTitle.setText(selectedAssessment.getAssessmentTitle());
            AssessmentDueDate.setText(tempDueDate);
            AssessmentType.setText(selectedAssessment.getAssessmentType());
            AssessmentStatus.setText(selectedAssessment.getAssessmentStatus());
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

        if (item.getItemId() == R.id.action_edit_assessment) {
            Intent intent = new Intent(this, edit_assessment_view.class);
            intent.putExtra("assessmentId", assessmentId);
            intent.putExtra("courseId", courseId);
            //System.out.println(termId);
            this.startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
