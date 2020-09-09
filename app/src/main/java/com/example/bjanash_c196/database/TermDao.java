package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity termEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermEntity> terms);

    @Delete
    void deleteTerm(TermEntity termEntity);

    @Query("SELECT * FROM terms WHERE :termTitle = termTitle")
    TermEntity getTermbyTitle(String termTitle);

    @Query("SELECT * FROM terms ORDER BY termStartDate DESC")
    LiveData<List<TermEntity>> getAllTerms();

    @Query("DELETE FROM terms")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM terms")
    String getCount();
}


