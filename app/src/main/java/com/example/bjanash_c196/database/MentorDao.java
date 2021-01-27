package com.example.bjanash_c196.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface MentorDao {
    @Query("SELECT * FROM mentors WHERE courseIdFk = :courseId ORDER BY mentorId")
    List<MentorEntity> getMentorList(int courseId);

    @Query("SELECT * FROM mentors WHERE courseIdFk = :courseId and mentorId = :mentorId")
    MentorEntity getMentor (int courseId, int mentorId);

    @Query("INSERT INTO mentors (courseIdFk, mentorName)\n" + "VALUES(:courseId, \"Mentor Name\");")
    void addMentor(int courseId);

    @Query("SELECT * FROM mentors")
    List<MentorEntity> getAllMentors();

    @Query("DELETE FROM mentors")
    public void destroyMentorsInTable();

    @Insert
    void insertMentor (MentorEntity mentors);

    @Insert
    void insertAllMentors (MentorEntity... mentors);

    @Update
    void updateMentor (MentorEntity mentors);

    @Delete
    void deleteMentor (MentorEntity mentors);
}
