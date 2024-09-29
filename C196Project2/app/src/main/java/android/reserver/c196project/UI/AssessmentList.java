package android.reserver.c196project.UI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AssessmentList extends AppCompatActivity {
    private Repository repository;
    int cID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        Intent courseIntent=getIntent();
        cID=courseIntent.getIntExtra("courseid",0);
        Button cancelAssess=findViewById(R.id.cancelAssessment);
        cancelAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FloatingActionButton floatingActionButton=findViewById(R.id.addassessment);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AssessmentList.this,AssessmentDetails.class);
                intent.putExtra("courseid",cID);
                activityResultLauncher.launch(intent);
            }
        });
        Intent myIntent=getIntent();
        cID=myIntent.getIntExtra("courseid",0);
        RecyclerView recyclerView= findViewById(R.id.assessmentRecycler);
        repository=new Repository(getApplication());
        List<Assessment> allAssessments=repository.getmAllAssessments();
        final AssessmentAdaptor assessmentAdaptor= new AssessmentAdaptor(this);
        recyclerView.setAdapter(assessmentAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdaptor.setAssessments(allAssessments);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_assessment_list,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.addassessment){
            //Toast.makeText(AssessmentList.this,"Assessment",Toast.LENGTH_LONG.show());//
            return true;
        }
        if(item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<Assessment> allAssessments= repository.getmAllAssessments();
        RecyclerView recyclerView=findViewById(R.id.assessmentRecycler);
        final AssessmentAdaptor assessmentAdaptor=new AssessmentAdaptor(this);
        recyclerView.setAdapter(assessmentAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdaptor.setAssessments(allAssessments);


    }
    public final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    finish();
                }
            });
}