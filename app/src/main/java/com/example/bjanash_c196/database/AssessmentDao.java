package com.example.bjanash_c196.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Query("SELECT * FROM assessments WHERE courseIdFk = :courseId ORDER BY assessmentId")
    List<AssessmentEntity> getAssessmentList(int courseId);

    @Query("SELECT * FROM assessments WHERE courseIdFk = :courseId and assessmentId = :assessmentId")
    AssessmentEntity getAssessment (int courseId, int assessmentId);

    @Query("INSERT INTO assessments (courseIdFk, assessmentTitle)\n" + "VALUES(:courseId, \"Assessment Name\");")
    void addAssessment(int courseId);

    @Query("SELECT * FROM assessments")
    List<AssessmentEntity> getAllAssessments();

    @Query("DELETE FROM assessments")
    public void destroyAssessmentsInTable();

    @Insert
    void insertAssessment (AssessmentEntity assessment);

    @Insert
    void insertAllAssessment (AssessmentEntity... assessment);

    @Update
    void updateAssessment (AssessmentEntity assessment);

    @Delete
    void deleteAssessment (AssessmentEntity assessment);

}
