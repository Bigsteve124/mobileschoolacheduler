package android.reserver.c196project.UI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Instructor;
import android.reserver.c196project.entities.Term;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CourseList extends AppCompatActivity {
    private Repository repository;
    int parentTermID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonCourse);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseList.this, CourseDetails.class);
                intent.putExtra("termid",parentTermID);
                activityResultLauncher.launch(intent);
            }

        });
        Intent myIntent=getIntent();
        parentTermID=myIntent.getIntExtra("termid",0);
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        repository = new Repository(getApplication());
        List<Course> allCourses = repository.getmAllCourses();
        final CourseAdaptor courseAdaptor = new CourseAdaptor(this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdaptor.setCourses(allCourses);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_list, menu);
        return true;
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        repository=new Repository(getApplication());
        List<Instructor> myInstructor=repository.getmAllInstructors();
        Instructor instructor=new Instructor(0, "Jane Doe","1123451234","jane.doe@gmail.com");
        repository.insertInstructor(instructor);
        List<Course> myCourse=repository.getmAllCourses();
        
        Course course=new Course(0,"Rap History","September 1 2024","September 30 2024","In Progress","Jane Doe","1123451234","jane.doe@gmail.com",1);
        repository.insertCourse(course);
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        repository = new Repository(getApplication());

        List<Course> allCourses = repository.getmAllCourses();
        final CourseAdaptor courseAdaptor = new CourseAdaptor(this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdaptor.setCourses(allCourses);
        if(item.getItemId()== android.R.id.home){
            this.finish();
            return true;
        }

        return true;

    }
    @Override
    protected void onResume() {
        super.onResume();

        List<Course> allCourses= repository.getmAllCourses();
        RecyclerView recyclerView=findViewById(R.id.courseRecycler);
        final CourseAdaptor courseAdaptor=new CourseAdaptor(this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdaptor.setCourses(allCourses);


    }
    public final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    finish();
                }
            });
    }
