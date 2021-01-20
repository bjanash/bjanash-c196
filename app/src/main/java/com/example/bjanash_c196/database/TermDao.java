package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface TermDao {

    @Query("SELECT * FROM terms ORDER BY termId")
    List<TermEntity> getTermList();

    @Query("SELECT * FROM terms WHERE termId = :termId ORDER BY termId")
    TermEntity getTerm (int termId);

    @Query("SELECT * FROM terms")
    List<TermEntity> getAllTerms();

    @Query("DELETE FROM terms")
    public void destroyTermsInTable();

    @Insert
    void insertTerm (TermEntity term);

    @Insert
    void insertAllTerms (TermEntity... term);

    @Update
    void updateTerm (TermEntity term);

    @Delete
    void deleteTerm (TermEntity term);

}


