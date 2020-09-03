package com.example.bjanash_c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity NoteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> notes);

    @Delete
    void deleteNote(NoteEntity NoteEntity);

    @Query("SELECT * FROM notes WHERE :noteTitle = noteTitle")
    NoteEntity getNotebyTitle(String noteTitle);

    @Query("SELECT * FROM notes ORDER BY noteTitle DESC")
    LiveData<List<NoteEntity>> getAll();

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM notes")
    String getCount();
}
