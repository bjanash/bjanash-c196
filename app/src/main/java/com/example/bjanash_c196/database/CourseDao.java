package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(CourseEntity courseEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CourseEntity> courses);

    @Delete
    void deleteCourse(CourseEntity courseEntity);

    @Query("SELECT * FROM courses WHERE :courseTitle = courseTitle")
    CourseEntity getCoursebyTitle(String courseTitle);

    @Query("SELECT * FROM courses ORDER BY courseStart DESC")
    LiveData<List<CourseEntity>> getAllCourses();

    @Query("DELETE FROM courses")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM courses")
    String getCount();
}
