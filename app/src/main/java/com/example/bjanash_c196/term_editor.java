package com.example.bjanash_c196;

import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.TermEditorViewModel;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bjanash_c196.utilities.Constants.END_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.START_TERM_TITLE_ID;
import static com.example.bjanash_c196.utilities.Constants.TERM_TITLE_ID;


public class term_editor extends AppCompatActivity {

    @BindView(R.id.TermTitle)
    TextView mTermTitle;

    @BindView(R.id.TermStart)
    TextView mTermStart;

    @BindView(R.id.TermEnd)
    TextView mTermEnd;


    private TermEditorViewModel mTermViewModel;
    private boolean mNewTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_check_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initTermViewModel();

    }
    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    private void initTermViewModel() {
        mTermViewModel = new ViewModelProvider(this).get(TermEditorViewModel.class);

        mTermViewModel.mLiveTerm.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                //Need to convert data type so that Dates can be brought up. This is where I left off.


                mTermTitle.setText(termEntity.getTermTitle());
                mTermStart.setText(termEntity.getTermStartDate());
                mTermEnd.setText(termEntity.getTermEndDate());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Term");
            mNewTerm = true;
        } else {
            setTitle("Edit Term");
            String termId = (String) extras.get(TERM_TITLE_ID);
            String termStartDate = (String) extras.get(START_TERM_TITLE_ID);
            String termEndDate = (String) extras.get(END_TERM_TITLE_ID);
            mTermViewModel.loadData(termId, termStartDate, termEndDate);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNewTerm){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.term_editor_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            mTermViewModel.deleteTerm();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            saveAndReturn();
    }

    private void saveAndReturn() {

        mTermViewModel.saveTerm(mTermTitle.getText().toString(), mTermStart.getText().toString(), mTermEnd.getText().toString());
        finish();
    }

}
