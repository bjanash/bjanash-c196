package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class detailed_course_view extends AppCompatActivity {

    ListView notes_listView;
    ListView assessments_listView;
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
    CourseEntity selectedCourse;
    TermEntity selectedTerm;
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
        MentorName = findViewById(R.id.MentorName);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        EmailAddress = findViewById(R.id.EmailAddress);

        assessments_listView = findViewById(R.id.assessments_listView);
        notes_listView = findViewById(R.id.notes_listView);

        updateViews();
        updateAssessmentsList();
        updateNotesList();

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

        FloatingActionButton fab_AssessmentAdd = findViewById(R.id.fab_AddAssessment);
        fab_AssessmentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                AssessmentEntity tempAssessment1 = new AssessmentEntity();
                tempAssessment1.setCourseIdFk(courseId);
                tempAssessment1.setAssessmentTitle("Assessment Added");
                tempAssessment1.setAssessmentStatus("change status");
                tempAssessment1.setAssessmentType("change type");
                calendar.add(Calendar.MONTH, 1);
                tempAssessment1.setAssessmentDueDate(calendar.getTime());
                db.assessmentDao().insertAssessment(tempAssessment1);
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


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
