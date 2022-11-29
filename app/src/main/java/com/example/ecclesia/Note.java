package com.example.ecclesia;

public class Note {
    int id;
    String noteTitle, noteContents, noteDate, noteTime;

    Note() {}

    public Note(String noteTitle, String noteContents, String noteDate, String noteTime) {
        this.noteTitle = noteTitle;
        this.noteContents = noteContents;
        this.noteDate = noteDate;
        this.noteTime = noteTime;
    }

    public Note(int id, String noteTitle, String noteContents, String noteDate, String noteTime) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContents = noteContents;
        this.noteDate = noteDate;
        this.noteTime = noteTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContents() {
        return noteContents;
    }

    public void setNoteContents(String noteContents) {
        this.noteContents = noteContents;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }
}
