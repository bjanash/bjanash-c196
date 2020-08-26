package com.example.bjanash_c196.model;

public class NoteEntity {
    private String noteTitle;
    private String noteContent;

    public NoteEntity(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public NoteEntity() {
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

    @Override
    public String toString() {
        return "NoteEntity{" +
                "noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                '}';
    }
}
