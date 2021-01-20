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
public interface NoteDao {

    @Query("SELECT * FROM notes WHERE courseIdFk = :courseId ORDER BY noteId")
    List<NoteEntity> getNoteList(int courseId);

    @Query("SELECT * FROM notes WHERE courseIdFk = :courseId and noteId = :noteId")
    NoteEntity getNote (int courseId, int noteId);

    @Query("INSERT INTO notes (courseIdFk, noteTitle)\n" + "VALUES(:courseId, \"Note Name\");")
    void addNote(int courseId);

    @Query("SELECT * FROM notes")
    List<NoteEntity> getAllNotes();

    @Query("DELETE FROM notes")
    public void destroyNotesInTable();

    @Insert
    void insertNote (NoteEntity note);

    @Insert
    void insertAllNotes (NoteEntity... note);

    @Update
    void updateNote (NoteEntity note);

    @Delete
    void deleteNote (NoteEntity note);
}
