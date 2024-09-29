package android.reserver.c196project.DAO;

import android.reserver.c196project.entities.Instructor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Instructor instructor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllInstructors(List<Instructor> instructors);

    @Update
    void update(Instructor instructor);
    @Delete
    void delete(Instructor instructor);
    @Query("SELECT * FROM instructor ")
    List<Instructor> getAllInstructors();

    @Query("SELECT * FROM instructor WHERE instructorID= :instructorID")
    List<Instructor> getInstructorID(int instructorID);

}