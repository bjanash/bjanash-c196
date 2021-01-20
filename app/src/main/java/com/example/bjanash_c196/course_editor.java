package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.CourseEditorViewModel;
import com.example.bjanash_c196.ViewModel.NoteEditorViewModel;
import com.example.bjanash_c196.ViewModel.TermEditorViewModel;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.COURSE_STATUS_ID;
import static com.example.bjanash_c196.utilities.Constants.COURSE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.EMAIL_ADDRESS_ID;
import static com.example.bjanash_c196.utilities.Constants.END_COURSE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.END_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.MENTOR_NAME_ID;
import static com.example.bjanash_c196.utilities.Constants.NOTE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.PHONE_NUMBER_ID;
import static com.example.bjanash_c196.utilities.Constants.START_COURSE_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.START_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;

public class course_editor extends AppCompatActivity {

    @BindView(R.id.CourseTitle)
    TextView mCourseTitle;

    @BindView(R.id.CourseStart)
    TextView mCourseStart;

    @BindView(R.id.CourseEnd)
    TextView mCourseEnd;

    @BindView(R.id.CourseStatus)
    TextView mCourseStatus;

    @BindView(R.id.MentorName)
    TextView mMentorName;

    @BindView(R.id.PhoneNumber)
    TextView mPhoneNumber;

    @BindView(R.id.EmailAddress)
    TextView mEmailAddress;

    @BindView(R.id.TermTitle)
    TextView mTermTitle;

    private CourseEditorViewModel mCourseViewModel;
    //private TermEditorViewModel mTermViewModel;
    private boolean mNewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_check_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initCourseViewModel();
        //initTermViewModel();

    }

    private void initCourseViewModel() {
        mCourseViewModel = new ViewModelProvider(this).get(CourseEditorViewModel.class);

        mCourseViewModel.mLiveCourse.observe(this, new Observer<CourseEntity>() {
            @Override
            public void onChanged(CourseEntity courseEntity) {
                mCourseTitle.setText(courseEntity.getCourseTitle());
                //mCourseStart.setText(courseEntity.getCourseStart());
                //mCourseEnd.setText(courseEntity.getCourseEnd());
                mCourseStatus.setText(courseEntity.getCourseStatus());
                mMentorName.setText(courseEntity.getMentorName());
                mPhoneNumber.setText(courseEntity.getPhoneNumber());
                mEmailAddress.setText(courseEntity.getEmailAddress());
               // mTermTitle.setText(courseEntity.getTermTitle());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Course");
            //initTermViewModel();
            mNewCourse = true;
        } else {
            setTitle("Edit Course");
            String courseId = (String) extras.get(COURSE_TITLE_ID);
            String courseStart = (String) extras.get(START_COURSE_TITLE_ID);
            String courseEnd = (String) extras.get(END_COURSE_TITLE_ID);
            String courseStatus = (String) extras.get(COURSE_STATUS_ID);
            String mentorName = (String) extras.get(MENTOR_NAME_ID);
            String phoneNumber = (String) extras.get(PHONE_NUMBER_ID);
            String emailAddress = (String) extras.get(EMAIL_ADDRESS_ID);
            String termTitle = (String) extras.get(TERM_TITLE_ID);
            //mCourseViewModel.loadData(courseId, courseStart, courseEnd, courseStatus, mentorName, phoneNumber, emailAddress, termTitle);
        }

    }

     /*private void initTermViewModel() {
        mTermViewModel = new ViewModelProvider(this).get(TermEditorViewModel.class);

        mTermViewModel.mLiveTerm.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                //Need to convert data type so that Dates can be brought up. This is where I left off

                mTermTitle.setText(termEntity.getTermTitle());
                //mTermStart.setText(termEntity.getTermStartDate());
                //mTermEnd.setText(termEntity.getTermEndDate());

            }
        });
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNewCourse){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.course_editor_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            //mCourseViewModel.deleteCourse();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {

        //mCourseViewModel.saveCourse(mCourseTitle.getText().toString(), mCourseStart.getText().toString(), mCourseEnd.getText().toString(), mCourseStatus.getText().toString(), mMentorName.getText().toString(), mPhoneNumber.getText().toString(),mEmailAddress.getText().toString(),mTermTitle.getText().toString());
        finish();
    }

}
