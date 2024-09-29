package android.reserver.c196project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteTitle;
    private String noteText;
    private int assessmentID;

    public Note( int noteID,String noteTitle,String noteText, int assessmentID){
        this.noteID=noteID;
        this.noteTitle=noteTitle;
        this.noteText=noteText;
        this.assessmentID=assessmentID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
}
