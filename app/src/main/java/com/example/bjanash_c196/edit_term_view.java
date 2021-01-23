package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class edit_term_view extends AppCompatActivity {
    TextView TermTitle;
    TextView TermEnd;
    TextView TermStart;
    Intent intent;
    int termId;
    AppDatabase db;
    SimpleDateFormat formatter;
    TermEntity selectedTerm;
   // Button saveTermButton = findViewById(R.id.termSaveButton);
   // Button deleteTermButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);
        db = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        setTitle("Edit Term");
        termId = intent.getIntExtra("termId", -1);
        System.out.println(termId);
        selectedTerm = db.termDao().getTerm(termId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        TermTitle = findViewById(R.id.TermTitle);
        TermStart = findViewById(R.id.TermStart);
        TermEnd = findViewById(R.id.TermEnd);

        updateViews();

        Button saveTermButton = findViewById(R.id.termSaveButton);
        saveTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String termTitle = TermTitle.getText().toString();
                    String termStartDate = TermStart.getText().toString();
                    String termEndDate = TermEnd.getText().toString();
                    Date formattedStartDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(termStartDate);
                    Date formattedEndDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(termEndDate);

                    TermEntity updatedTerm = db.termDao().getTerm(termId);
                    updatedTerm.setTermTitle(termTitle);
                    updatedTerm.setTermStartDate(formattedStartDate);
                    updatedTerm.setTermEndDate(formattedEndDate);
                    db.termDao().updateTerm(updatedTerm);
                    Intent intent = new Intent(getApplicationContext(), detailed_term_view.class);
                    intent.putExtra("termId", termId);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button deleteTermButton = findViewById(R.id.termDeleteButton);
        deleteTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TermEntity updatedTerm = db.termDao().getTerm(termId);
                    db.termDao().deleteTerm(updatedTerm);
                    Intent intent = new Intent(getApplicationContext(), list_of_terms.class);

                    startActivity(intent);

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void updateViews() {
        if (selectedTerm != null) {
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
}
