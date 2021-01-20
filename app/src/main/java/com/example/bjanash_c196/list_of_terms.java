package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.database.AppDatabase;
import com.example.bjanash_c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

public class list_of_terms extends AppCompatActivity {

    AppDatabase db;
    ListView listView;

    @Override
    protected void onResume(){
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_terms);
        setTitle("Term List");
        listView = findViewById(R.id.terms_listView);
        db = AppDatabase.getInstance(getApplicationContext());

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Position clicked: " + position);
                int termId;
                List<TermEntity> termEntityList = db.termDao().getTermList();
                termId = termEntityList.get(position).getTermId();
                Intent intent = new Intent(getApplicationContext(), detailed_term_view.class);
                intent.putExtra("termId", termId);

                System.out.println("termId selected = " + String.valueOf(termId));
                startActivity(intent);

            }
        });

       updateList();

        FloatingActionButton fab_addTerm = findViewById(R.id.fab_addTerm);
        fab_addTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                TermEntity tempTerm1 = new TermEntity();
                tempTerm1.setTermTitle("Term Added");
                tempTerm1.setTermStartDate(calendar.getTime());
                calendar.add(Calendar.MONTH, 1);
                tempTerm1.setTermEndDate(calendar.getTime());
                db.termDao().insertTerm(tempTerm1);
                updateList();

            }
        });

    }

    private void updateList() {
        List<TermEntity> allTerms = db.termDao().getTermList();
        System.out.println("Number of rows in table: " + allTerms.size());

        String[] items = new String[allTerms.size()];
        if(!allTerms.isEmpty()) {
            for(int i = 0; i < allTerms.size(); i++) {
                items[i] = allTerms.get(i).getTermTitle();
                System.out.println("Term in position = " + i + " with Name = " + items[i]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

       return super.onOptionsItemSelected(item);
    }


}
