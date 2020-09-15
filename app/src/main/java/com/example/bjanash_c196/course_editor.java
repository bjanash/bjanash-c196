package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.CourseEditorViewModel;
import com.example.bjanash_c196.ViewModel.NoteEditorViewModel;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class course_editor extends AppCompatActivity {

    @BindView(R.id.CourseTitle)
    TextView mCourseTitle;

    private CourseEditorViewModel mCourseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initCourseViewModel();

    }

    private void initCourseViewModel() {
        mCourseViewModel = new ViewModelProvider(this).get(CourseEditorViewModel.class);

        mCourseViewModel.mLiveCourse.observe(this, new Observer<CourseEntity>() {
            @Override
            public void onChanged(CourseEntity courseEntity) {
                mCourseTitle.setText(courseEntity.getCourseTitle());

            }
        });


    }

}
