package com.example.bjanash_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.bjanash_c196.ViewModel.AssessmentViewModel;
import com.example.bjanash_c196.ViewModel.TermViewModel;
import com.example.bjanash_c196.database.AssessmentEntity;
import com.example.bjanash_c196.database.CourseEntity;
import com.example.bjanash_c196.database.NoteEntity;
import com.example.bjanash_c196.database.TermEntity;
import com.example.bjanash_c196.ui.TermsAdapter;
import com.example.bjanash_c196.utilities.SampleData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class list_of_terms extends AppCompatActivity {

    @BindView(R.id.terms_recyclerView)
    RecyclerView mtermsRecyclerView;
    private TermViewModel mTermViewModel;

    private List<TermEntity> termsData = new ArrayList<>();
    private TermsAdapter mTermAdapter;

    @OnClick(R.id.fab_addTerm)
    void fabClickHandler() {
        Intent intent = new Intent(this, term_editor.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_terms);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initTermViewModel();
        initTermsRecyclerView();
    }


    private void initTermsRecyclerView(){
        mtermsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mtermsRecyclerView.setLayoutManager(layoutManager);
    }

    private void initTermViewModel() {
        final Observer<List<TermEntity>> termsObserver = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                termsData.clear();
                termsData.addAll(termEntities);

                if(mTermAdapter == null){
                    mTermAdapter = new TermsAdapter(termsData, list_of_terms.this);
                    mtermsRecyclerView.setAdapter(mTermAdapter);
                } else{
                    mTermAdapter.notifyDataSetChanged();
                }
            }
        };

        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        mTermViewModel.mTerms.observe(this,termsObserver);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleTermsData();
            return true;
        } else if(id == R.id.action_delete_all){
            deleteAllTerms();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllTerms() {
        mTermViewModel.deleteAllTerms();
    }

    private void addSampleTermsData() {
        mTermViewModel.addSampleTermsData();
    }

}
