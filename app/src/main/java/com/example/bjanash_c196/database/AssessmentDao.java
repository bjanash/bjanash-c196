package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(AssessmentEntity assessmentEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AssessmentEntity> assessments);

    @Delete
    void deleteAssessment(AssessmentEntity assessmentEntity);

    @Query("SELECT * FROM assessments WHERE :assessmentTitle = assessmentTitle")
    AssessmentEntity getAssessmentbyTitle(String assessmentTitle);

    @Query("SELECT * FROM assessments ORDER BY assessmentDueDate DESC")
    LiveData<List<AssessmentEntity>> getAll();

    @Query("DELETE FROM assessments")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM assessments")
    String getCount();

}
