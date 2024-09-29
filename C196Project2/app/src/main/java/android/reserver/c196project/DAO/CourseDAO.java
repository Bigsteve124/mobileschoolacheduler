package android.reserver.c196project.DAO;
import android.reserver.c196project.entities.Course;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);
    @Update
    void update(Course course);
    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course ORDER BY courseID ASC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM course WHERE courseID= :courseID")
    Course getCourseID(int courseID);

    @Query("SELECT * FROM course WHERE termID= :termID")
    List<Course> getAssociatedCourses(int termID);
}
