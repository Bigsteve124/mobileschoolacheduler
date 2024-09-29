package android.reserver.c196project.database;

import android.content.Context;
import android.reserver.c196project.DAO.CourseDAO;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.DAO.InstructorDAO;
import android.reserver.c196project.DAO.NoteDAO;
import android.reserver.c196project.DAO.TermDAO;
import android.reserver.c196project.DAO.AssessmentDAO;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Term;
import android.reserver.c196project.entities.Instructor;
import android.reserver.c196project.entities.Note;

@androidx.room.Database(entities = {Course.class,Assessment.class,Instructor.class,Note.class,Term.class},version=24,exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract NoteDAO noteDAO();
    public abstract TermDAO termDAO();

    private static volatile   Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

}

