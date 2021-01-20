package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.ui.CoursesAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class list_of_courses extends AppCompatActivity {

    public String termTitle;

    @BindView(R.id.recyclerViewCourses)
    RecyclerView mcoursesRecyclerView;
    private CourseViewModel mCourseViewModel;

    private List<CourseEntity> coursesData = new ArrayList<>();
    private CoursesAdapter mCourseAdapter;

    @OnClick(R.id.fab_addCourse)
    void fabClickHandler() {
        Intent intent = new Intent(this, course_editor.class);
        startActivity(intent);
        //send the Term Name from here - BJANASH
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        //initCourseViewModel();
        //initCourseRecyclerView();


    }

    private void initCourseRecyclerView(){
        mcoursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mcoursesRecyclerView.setLayoutManager(layoutManager);
    }
//ONLY ADD COURSES THAT ARE ASSIGNED TO THE TERM
    private void initCourseViewModel() {
        final Observer<List<CourseEntity>> coursesObserver = new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                coursesData.clear();
                coursesData.addAll(courseEntities);

                if(mCourseAdapter == null){
                    mCourseAdapter = new CoursesAdapter(coursesData, list_of_courses.this);
                    mcoursesRecyclerView.setAdapter(mCourseAdapter);
                } else{
                    mCourseAdapter.notifyDataSetChanged();
                }
            }
        };
        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.mCourses.observe(this,coursesObserver);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleCoursesData();
            return true;
        } else if(id == R.id.action_delete_all){
           // deleteAllCourses();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void addSampleCoursesData() {
       // mCourseViewModel.addSampleCoursesData();
    }
    //private void deleteAllCourses() {
        //mCourseViewModel.deleteAllCourses();
    //}

}
