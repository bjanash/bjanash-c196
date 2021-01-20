package com.example.bjanash_c196.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
//Fields & Table
@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "courseId",
                childColumns = "courseIdFk",
                onDelete = CASCADE))

//Constructors
public class NoteEntity {
    @PrimaryKey (autoGenerate = true) @NonNull
    private int noteId;
    @ColumnInfo(name = "courseIdFk", index = true)
    private int courseIdFk;
    @ColumnInfo(name = "noteTitle")
    private String noteTitle;
    @ColumnInfo(name = "noteContent")
    private String noteContent;

    public NoteEntity(int noteId, int courseIdFk, String noteTitle, String noteContent) {
        this.noteId = noteId;
        this.courseIdFk = courseIdFk;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    @Ignore
    public NoteEntity() {
    }

//Getters & Setters
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int assessmentId) {
        this.noteId = noteId;
    }

    public int getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(int courseIdFk) {
        this.courseIdFk = courseIdFk;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

//To String
    @Override
    public String toString() {
        return "NoteEntity{" +
                "noteId=" + noteId +
                ", courseIdFk=" + courseIdFk +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent=" + noteContent +
                '}';
    }
}
