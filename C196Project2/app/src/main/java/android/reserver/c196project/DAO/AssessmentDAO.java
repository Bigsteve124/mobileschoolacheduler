package android.reserver.c196project.DAO;

import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Assessment assessment);
    @Update
    void update(Assessment assessment);
    @Delete
    void delete(Assessment assessment);
    @Query("SELECT * FROM assessment WHERE assessmentID=:assessmentid")
    Assessment getAssessmentID(int assessmentid);
    @Query("SELECT * FROM assessment")
    List<Assessment>getAllAssessments();
    @Query("SELECT * FROM assessment WHERE courseID=:courseid")
    List<Assessment> getAssociatedAssessments( int courseid);


}
