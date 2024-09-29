package android.reserver.c196project.DAO;
import android.reserver.c196project.entities.Term;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Term term);
    @Update
    void update(Term term);
    @Delete
    void delete(Term term);
    @Query("SELECT * FROM term")
    List<Term> getAllTerms();
    @Query("SELECT * FROM term WHERE termID= :termID")
    Term getTermId(int termID);


}