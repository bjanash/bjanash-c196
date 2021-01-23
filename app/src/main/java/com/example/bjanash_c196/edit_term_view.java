package com.example.bjanash_c196;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bjanash_c196.database.AppDatabase;

import java.text.SimpleDateFormat;

public class edit_term_view extends AppCompatActivity {
    TextView TermTitle;
    TextView TermEnd;
    TextView TermStart;
    Intent intent;
    int termId;
    AppDatabase db;
    SimpleDateFormat formatter;


}
