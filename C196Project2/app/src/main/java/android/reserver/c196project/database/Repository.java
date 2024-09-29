package android.reserver.c196project.database;

import android.app.Application;
import android.reserver.c196project.DAO.AssessmentDAO;
import android.reserver.c196project.DAO.CourseDAO;
import android.reserver.c196project.DAO.InstructorDAO;
import android.reserver.c196project.DAO.NoteDAO;
import android.reserver.c196project.DAO.TermDAO;
import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Instructor;
import android.reserver.c196project.entities.Note;
import android.reserver.c196project.entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final CourseDAO mCourseDAO;
    private final AssessmentDAO mAssessmentDAO;
    private final InstructorDAO mInstructorDAO;
    private final NoteDAO mNoteDAO;
    private final TermDAO mTermDAO;

    private List<Course> mAllCourses;
    private List<Instructor> mAllInstructors;
    private List<Assessment> mAllAssessments;
    private List<Note> mAllNotes;
    private List<Term> mAllTerms;
    private List<Course>mAssociatedCourses;
    private Course mGetCourse;
    private Term mAssoociatedTerm;
    private Assessment mAssociatedAssessment;
    private Assessment mGetAssessment;
    private List<Assessment>mAssociatedAssessments;

    private static final int NUMBER_OF_THREADS = 8;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        Database db = Database.getDatabase(application);
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
        mInstructorDAO = db.instructorDAO();
        mNoteDAO = db.noteDAO();
        mTermDAO = db.termDAO();
    }

    public List<Course> getmAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllCourses;
    }

    public List<Course> getmAssociatedCourses(int termID) {
        databaseExecutor.execute(() -> {
            mAssociatedCourses = (List<Course>) mCourseDAO.getAssociatedCourses(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAssociatedCourses;
    }
    public Course getCourse(int courseID) {
        databaseExecutor.execute(() -> {
            mGetCourse = mCourseDAO.getCourseID(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mGetCourse;
    }

    public void insertCourse(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Instructor> getmAllInstructors() {
        databaseExecutor.execute(() -> {
            mAllInstructors = mInstructorDAO.getAllInstructors();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllInstructors;
    }
    private List<Instructor> getmAssociatedInstructors (int courseID) {
        databaseExecutor.execute(() -> {
            mAllInstructors = (List<Instructor>) mInstructorDAO.getInstructorID(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllInstructors;
    }

    public void insertInstructor(Instructor instructor) {
        databaseExecutor.execute(() -> {
            mInstructorDAO.insert(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteInstructor(Instructor instructor) {
        databaseExecutor.execute(() -> {
            mInstructorDAO.delete(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateInstructor(Instructor instructor) {
        databaseExecutor.execute(() -> {
            mInstructorDAO.update(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Assessment getAssessment(int assessmentid){
        databaseExecutor.execute(()->{
            mGetAssessment=mAssessmentDAO.getAssessmentID(assessmentid);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mGetAssessment;
    }
    public List<Assessment> getmAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllAssessments;
    }

    public List<Assessment> getmAssociatedAssessments(int courseID) {
        databaseExecutor.execute(() -> {
            mAssociatedAssessments = (List<Assessment>) mAssessmentDAO.getAssociatedAssessments(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAssociatedAssessments;
    }

    public void insertAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Term> getmAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllTerms;
    }

    public Term getmAssociatedTerms(int termID) {
        databaseExecutor.execute(() -> {
            mAssoociatedTerm= mTermDAO.getTermId(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAssoociatedTerm;
    }

    public void insertTerm(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteTerm(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateTerm(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Note> getAllNotes() {
        databaseExecutor.execute(() -> {
            mAllNotes = mNoteDAO.getAllNotes();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllNotes;
    }

    private List<Note> getAssociatedNotes(int noteID) {
        databaseExecutor.execute(() -> {
            mAllNotes = (List<Note>) mNoteDAO.getNoteID(noteID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllNotes;
    }

    public void insertNote(Note note) {
        databaseExecutor.execute(() -> {
            mNoteDAO.insert(note);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(Note note) {
        databaseExecutor.execute(() -> {
            mNoteDAO.delete(note);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(Note note) {
        databaseExecutor.execute(() -> {
            mNoteDAO.update(note);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
