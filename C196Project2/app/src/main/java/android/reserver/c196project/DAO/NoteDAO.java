package android.reserver.c196project.DAO;
import android.reserver.c196project.entities.Note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);
    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE noteID= :noteID")
    List<Note> getNoteID(int noteID);


}