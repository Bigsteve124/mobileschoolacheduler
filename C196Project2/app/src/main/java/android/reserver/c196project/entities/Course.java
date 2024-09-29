package android.reserver.c196project.entities;

import android.widget.Spinner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseTitle;
    private String startDate;
    private String endDate;
    private String courseStatus;
    private String instructorName;
    private int termID;
     private String instructorPhoneNum;
     private String instructorEmail;
     private String note;



    public Course(int courseID, String courseTitle, String startDate, String endDate, String courseStatus, String instructorName, String instructorPhoneNum, String instructorEmail, int termID) {
        this.courseID=courseID;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhoneNum= instructorPhoneNum;
        this.instructorEmail=instructorEmail;
        this.termID=termID;


    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhoneNum() {
        return instructorPhoneNum;
    }

    public void setInstructorPhoneNum(String instructorPhoneNum) {
        this.instructorPhoneNum = instructorPhoneNum;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;

    }
}
