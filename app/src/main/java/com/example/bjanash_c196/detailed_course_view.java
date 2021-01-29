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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.MentorEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.bjanash_c196.AlertReminder.LOG_INFO;

public class detailed_course_view extends AppCompatActivity {

    private static final int NOTIFY_ID = 747;
    ListView notes_listView;
    ListView assessments_listView;
    ListView mentor_ListView;
    TextView CourseTitle;
    TextView CourseEnd;
    TextView CourseStart;
    TextView CourseStatus;


    Intent intent;
    int termId;
    int courseId;

    AppDatabase db;
    CourseEntity selectedCourse;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course_view);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Course Detail View");
        termId = intent.getIntExtra("termId", -1);
        courseId = intent.getIntExtra("courseId", -1);
        System.out.println("Term ID" + termId);
        System.out.println("Course ID" + courseId);
        selectedCourse = db.courseDao().getCourse(termId, courseId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        CourseTitle = findViewById(R.id.CourseTitle);
        CourseEnd = findViewById(R.id.CourseEnd);
        CourseStart = findViewById(R.id.CourseStart);
        CourseStatus = findViewById(R.id.CourseStatus);

        assessments_listView = findViewById(R.id.assessments_listView);
        notes_listView = findViewById(R.id.notes_listView);
        mentor_ListView = findViewById(R.id.mentor_ListView);

        updateViews();
        updateAssessmentsList();
        updateNotesList();
        updateMentorsList();
        notifyChannel();


        Button setAlertCourseButton = findViewById(R.id.setAlertCourseButton);
        setAlertCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Set Alert (Click)");

                Calendar calDate = Calendar.getInstance();
                calDate.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDateEnd = calDate.getTime();
                try {
                    alertDateEnd = formatter.parse(CourseEnd.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setAlert(alertDateEnd);
            }
        });

        Button setAlertCourseButton2 = findViewById(R.id.setAlertCourseButton2);
        setAlertCourseButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Set Alert (Click)");

                Calendar calDate = Calendar.getInstance();
                calDate.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDateStart = calDate.getTime();
                try {
                    alertDateStart = formatter.parse(CourseStart.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setAlert(alertDateStart);
            }
        });

        assessments_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Assessment clicked " + position);
                Intent intent = new Intent(getApplicationContext(), detailed_assessment_view.class);
                int assessmentId = db.assessmentDao().getAssessmentList(courseId).get(position).getAssessmentId();
                intent.putExtra("courseId", courseId);
                intent.putExtra("assessmentId", assessmentId);
                System.out.println("AssessmentID " + assessmentId);
                System.out.println("courseID " + courseId);
                startActivity(intent);
            }
        });

        notes_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Note clicked " + position);
                Intent intent = new Intent(getApplicationContext(), detailed_note_view.class);
                int noteId = db.noteDao().getNoteList(courseId).get(position).getNoteId();
                intent.putExtra("courseId", courseId);
                intent.putExtra("noteId", noteId);
                System.out.println("courseId " + courseId);
                System.out.println("noteId " + noteId);
                startActivity(intent);

            }
        });

        mentor_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Mentor clicked " + position);
                Intent intent = new Intent(getApplicationContext(), detailed_mentor_view.class);
                int mentorId = db.mentorDao().getMentorList(courseId).get(position).getMentorId();
                intent.putExtra("courseId", courseId);
                intent.putExtra("mentorId", mentorId);
                System.out.println("courseId " + courseId);
                System.out.println("mentorId " + mentorId);
                startActivity(intent);

            }
        });

        FloatingActionButton fab_AssessmentAdd = findViewById(R.id.fab_AddAssessment);
        fab_AssessmentAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<AssessmentEntity> courseAssessments = db.assessmentDao().getAssessmentList(courseId);
                Calendar calendar = Calendar.getInstance();
                AssessmentEntity tempAssessment1 = new AssessmentEntity();
                tempAssessment1.setCourseIdFk(courseId);
                tempAssessment1.setAssessmentTitle("Assessment Added");
                tempAssessment1.setAssessmentStatus("change status");
                tempAssessment1.setAssessmentType("change type");
                tempAssessment1.setAssessmentDueDate(calendar.getTime());
                calendar.add(Calendar.MONTH, 1);
                if(courseAssessments.size() <= 4){
                db.assessmentDao().insertAssessment(tempAssessment1); }
                else {};
                updateAssessmentsList();
            }
        });

        FloatingActionButton fab_NoteAdd = findViewById(R.id.fab_NoteAdd);
        fab_NoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                NoteEntity tempNote1 = new NoteEntity();
                tempNote1.setCourseIdFk(courseId);
                tempNote1.setNoteTitle("Note Added");
                tempNote1.setNoteContent("change content");
                db.noteDao().insertNote(tempNote1);
                updateNotesList();

            }
        });

        FloatingActionButton fab_MentorAdd = findViewById(R.id.fab_Mentor);
        fab_MentorAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MentorEntity tempMentor1 = new MentorEntity();
                tempMentor1.setCourseIdFk(courseId);
                tempMentor1.setMentorName("Mentor Added");
                tempMentor1.setPhoneNumber("change phone number");
                tempMentor1.setEmailAddress("change email address");
                db.mentorDao().insertMentor(tempMentor1);
                updateMentorsList();

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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

    private void setAlert(Date calDateProvided) {
        Calendar calDateNow = Calendar.getInstance();
        if(calDateProvided.getTime() > calDateNow.getTime().getTime()){
            Log.d(LOG_INFO, formatter.format(calDateProvided) + " notify date");
            Intent sendIntent = new Intent(getApplicationContext(), AlertReminder.class);
            sendIntent.putExtra("my_title", "Important Date");
            sendIntent.putExtra("my_message", "Course: " + CourseTitle.getText().toString() + " is starting/ending \n" + "Date and Time: " + formatter.format(calDateProvided));
            sendIntent.putExtra("notify_id", NOTIFY_ID);
            PendingIntent thisPendIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    NOTIFY_ID,
                    sendIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alertManage = (AlarmManager) getSystemService(ALARM_SERVICE);
            alertManage.set(AlarmManager.RTC, calDateProvided.getTime(), thisPendIntent);
            Toast.makeText(getApplicationContext(), "Alert set for: " + CourseTitle.getText().toString(), Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), formatter.format(calDateProvided) + " is a PAST DATE or TIME", Toast.LENGTH_LONG).show();
        }

    }

    private void updateAssessmentsList() {
        List<AssessmentEntity> allAssessment = new ArrayList<>();
        try {
            allAssessment = db.assessmentDao().getAssessmentList(courseId);
        } catch (Exception e) {
            System.out.println("didn't work");
        }

        String[] items = new String[allAssessment.size()];
        if(!allAssessment.isEmpty()){
            for(int i = 0; i < allAssessment.size(); i++) {
                items[i] = allAssessment.get(i).getAssessmentTitle();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        assessments_listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateNotesList() {

        List<NoteEntity> allNote = new ArrayList<>();
        try {
            allNote = db.noteDao().getNoteList(courseId);
        } catch (Exception e) {
            System.out.println("didn't work");
        }

        String[] items = new String[allNote.size()];
        if(!allNote.isEmpty()){
            for(int i = 0; i < allNote.size(); i++) {
                items[i] = allNote.get(i).getNoteTitle();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        notes_listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateMentorsList() {

        List<MentorEntity> allMentor = new ArrayList<>();
        try {
            allMentor = db.mentorDao().getMentorList(courseId);
        } catch (Exception e) {
            System.out.println("didn't work");
        }

        String[] items = new String[allMentor.size()];
        if(!allMentor.isEmpty()){
            for(int i = 0; i < allMentor.size(); i++) {
                items[i] = allMentor.get(i).getMentorName();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        mentor_ListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
        } else {
            selectedCourse = new CourseEntity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_edit_course) {
            Intent intent = new Intent(this, edit_course_view.class);
            intent.putExtra("termId", termId);
            intent.putExtra("courseId", courseId);
            System.out.println(termId);
            this.startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }


}
