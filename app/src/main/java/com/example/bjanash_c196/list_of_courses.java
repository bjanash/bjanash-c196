package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.ui.CoursesAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class list_of_courses extends AppCompatActivity {

    @BindView(R.id.recyclerViewCourses)
    RecyclerView mcoursesRecyclerView;

    private List<CourseEntity> coursesData = new ArrayList<>();

    private CoursesAdapter mCourseAdapter;
    private CourseViewModel mCourseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //sample data for courses
        coursesData.addAll(mCourseViewModel.mCourses);
        for(CourseEntity course : coursesData) {
            Log.i("courseLog", course.toString());
        }
        initTermsRecyclerView();
        initCourseViewModel();

    }

    private void initCourseViewModel() {
        mCourseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
    }

    private void initTermsRecyclerView(){
        mcoursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mcoursesRecyclerView.setLayoutManager(layoutManager);

        mCourseAdapter = new CoursesAdapter(coursesData, this);
        mcoursesRecyclerView.setAdapter(mCourseAdapter);
    }

}
