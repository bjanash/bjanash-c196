package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM courses WHERE termIdFk = :termId ORDER BY courseId")
    List<CourseEntity> getCourseList(int termId);

    @Query("SELECT * FROM courses WHERE termIdFk = :termId and courseId = :courseId")
    CourseEntity getCourse (int termId, int courseId);

    @Query("INSERT INTO courses (termIdFk, courseTitle)\n" + "VALUES(:termId, \"Course Name\");")
    void addCourse(int termId);

    @Query("SELECT * FROM courses")
    List<CourseEntity> getAllCourses();

    @Query("DELETE FROM courses")
    public void destroyCoursesInTable();

    @Insert
    void insertCourse (CourseEntity course);

    @Insert
    void insertAllCourses (CourseEntity... course);

    @Update
    void updateCourse (CourseEntity course);

    @Delete
    void deleteCourse (CourseEntity course);

}
