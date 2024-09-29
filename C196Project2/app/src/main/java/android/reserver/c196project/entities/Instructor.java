package android.reserver.c196project.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructor")
public class  Instructor {
    @PrimaryKey(autoGenerate = true)
    private int instructorID;
    private String instructorName;
    private String phoneNum;
    private String email;

    //Constructor
    public Instructor(int instructorID,String instructorName,String phoneNum, String email){
        this.instructorID=instructorID;
        this.instructorName=instructorName;
        this.phoneNum=phoneNum;
        this.email=email;

    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
