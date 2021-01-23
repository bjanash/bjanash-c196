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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class detailed_term_view extends AppCompatActivity {

    ListView courses_listView;
    TextView TermTitle;
    TextView TermEnd;
    TextView TermStart;
    Intent intent;
    int termId;
    int courseId;

    AppDatabase db;
    TermEntity selectedTerm;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_view);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Term Detail View");
        termId = intent.getIntExtra("termId", -1);
        System.out.println(termId);
        selectedTerm = db.termDao().getTerm(termId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        courses_listView = findViewById(R.id.courses_listView);
        TermTitle = findViewById(R.id.TermTitle);
        TermStart = findViewById(R.id.TermStart);
        TermEnd = findViewById(R.id.TermEnd);

        updateViews();

        courses_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Course clicked " + position);
                Intent intent = new Intent(getApplicationContext(), detailed_course_view.class);
                int courseId = db.courseDao().getCourseList(termId).get(position).getCourseId();
                intent.putExtra("termId", termId);
                intent.putExtra("courseId", courseId);
                startActivity(intent);
                
            }
        });
        
        updateCourseList();

        FloatingActionButton fab_addCourse = findViewById(R.id.fab_addCourse);
        fab_addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                CourseEntity tempCourse1 = new CourseEntity();
                tempCourse1.setTermIdFk(termId);
                tempCourse1.setCourseTitle("Course Added");
                tempCourse1.setCourseStatus("change status");
                tempCourse1.setMentorName("change mentor");
                tempCourse1.setPhoneNumber("change phone #");
                tempCourse1.setEmailAddress("change email");
                tempCourse1.setCourseStart(calendar.getTime());
                calendar.add(Calendar.MONTH, 1);
                tempCourse1.setCourseEnd(calendar.getTime());
                db.courseDao().insertCourse(tempCourse1);
                updateCourseList();

            }
        });
        

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateCourseList() {
        List<CourseEntity> allCourses = new ArrayList<>();
        try {
            allCourses = db.courseDao().getCourseList(termId);
        } catch (Exception e) {
            System.out.println("didn't work");
        }

        String[] items = new String[allCourses.size()];
        if(!allCourses.isEmpty()){
            for(int i = 0; i < allCourses.size(); i++) {
                items[i] = allCourses.get(i).getCourseTitle();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        courses_listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateViews() {
        if(selectedTerm != null){
            Date startDate = selectedTerm.getTermStartDate();
            Date endDate = selectedTerm.getTermEndDate();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            TermStart.setText(tempStart);
            TermEnd.setText(tempEnd);
            TermTitle.setText(selectedTerm.getTermTitle());
        } else {
            selectedTerm = new TermEntity();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.term_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case R.id.action_edit_term:
                Intent intent = new Intent(this, detailed_term_view.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectedTerm = db.termDao().getTerm(termId);
        updateViews();
    }


}
