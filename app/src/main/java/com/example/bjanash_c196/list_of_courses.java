package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.CourseViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.ui.AssessmentsAdapter;
import com.example.bjanash_c196.ui.CoursesAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
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
        initCourseRecyclerView();
        initCourseViewModel();

    }

    private void initCourseRecyclerView(){
        mcoursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mcoursesRecyclerView.setLayoutManager(layoutManager);
    }

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
        mCourseAdapter = new CoursesAdapter(coursesData, this);
        mcoursesRecyclerView.setAdapter(mCourseAdapter);
    }

    private void addSampleCoursesData() {
        mCourseViewModel.addSampleCoursesData();
    }
    private void deleteAllCourses() {
        mCourseViewModel.deleteAllCourses();
    }

}
