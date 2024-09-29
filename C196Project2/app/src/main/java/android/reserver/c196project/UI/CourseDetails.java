package android.reserver.c196project.UI;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.DAO.TermDAO;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Database;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Term;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.reserver.c196project.database.Repository;
import android.widget.Toast;

public class CourseDetails extends AppCompatActivity {
private Repository repository;
EditText editTitle;
EditText editStart;
EditText editEnd;
EditText editInstructorName;
EditText editInstructorPhone;
EditText editInstructorEmail;
EditText editNote;
String title;
String start;
String end;
String note;
String instructorName;
String instructorPhone;
String instructorEmail;
int courseID;
private Spinner statusSpinner;
private String selectedStatus;
int id;
int termid;
int assessmentid;
int cID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        editTitle = findViewById(R.id.coursetitletext);
        editStart = findViewById(R.id.startdatetext);
        editEnd = findViewById(R.id.enddatetext);
        editInstructorName = findViewById(R.id.nameText);
        editInstructorPhone=findViewById(R.id.phoneText);
        editInstructorEmail=findViewById(R.id.emailText);
        editNote = findViewById(R.id.noteText);
        id=getIntent().getIntExtra("id",courseID);
        title = getIntent().getStringExtra("title");
        start = getIntent().getStringExtra("start date");
        end = getIntent().getStringExtra("end date");
        instructorName = getIntent().getStringExtra("instructor name");
        instructorPhone = getIntent().getStringExtra("instructor phone number");
        instructorEmail = getIntent().getStringExtra("instructor email");
        note = getIntent().getStringExtra("note");
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        editInstructorName.setText(instructorName);
        editInstructorPhone.setText(instructorPhone);
        editInstructorEmail.setText(instructorEmail);
        editNote.setText(note);
        Intent courseIntent=getIntent();
        cID=courseIntent.getIntExtra("id",0);
        statusSpinner = findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.courseStatus, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(arrayAdapter);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button = findViewById(R.id.courseSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=editTitle.getText().toString();
                String start=editStart.getText().toString();
                String end=editEnd.getText().toString();
                String instructorName=editInstructorName.getText().toString();
                String instructorPhone=editInstructorPhone.getText().toString();
                String instructorEmail=editInstructorEmail.getText().toString();
                String spinnerStatus=selectedStatus;
                Course course=new Course(id,title,start,end,spinnerStatus,instructorName,instructorPhone,instructorEmail,termid);
                repository.insertCourse(course);
                setResult(RESULT_OK, getIntent());
                finish();



            }
        });
        Button button2=findViewById(R.id.courseCancel);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Spinner statusSpinner=findViewById(R.id.statusSpinner);
        //Course Status Options
        final String[] statusOptions={"In Progress","Completed","Dropped","Plan to Take"};
        //Array Adapter
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,statusOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
        Intent myIntent=getIntent();
        termid=myIntent.getIntExtra("termid",0);
        courseID=myIntent.getIntExtra("courseid",id);
        assessmentid=myIntent.getIntExtra("assessment id",assessmentid);

        RecyclerView recyclerView=findViewById(R.id.cdrecycler);
        repository= new Repository(getApplication());

        final AssessmentAdaptor assessmentAdaptor=new AssessmentAdaptor(this);
        recyclerView.setAdapter(assessmentAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdaptor.setAssessments(repository.getmAssociatedAssessments(courseID));


        Course course;
        if(courseID>0){
            course=repository.getCourse(courseID);
            int position=adapter.getPosition(course.getCourseStatus());
            statusSpinner.setSelection(position);
        }
        else {
            if(repository.getmAllCourses().isEmpty())
            {
                courseID = 1;
            }
            else
            {
                courseID = repository.getmAllCourses().get(repository.getmAllCourses().size() - 1).getCourseID() + 1;
            }
            course = new Course(courseID, title, start, end, instructorName, instructorPhone, instructorEmail, selectedStatus, termid);
        }



        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStatus= (statusOptions)[position];
            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });



        }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_course_details,menu);
        Button button=findViewById(R.id.courseSave);
        editTitle=findViewById(R.id.coursetitletext);
        editStart=findViewById(R.id.startdatetext);
        editEnd=findViewById(R.id.enddatetext);
        editInstructorName=findViewById(R.id.nameText);
        editInstructorPhone=findViewById(R.id.phoneText);
        editInstructorEmail=findViewById(R.id.emailText);
        editNote=findViewById(R.id.noteText);


        return true;
    }
    public Date dateParse(String time){
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        try{
            Date date=format.parse(time);
            return date;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;

    }

    private void scheduleNotification(Context context, String title, String text, Date triggerDate) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(CourseDetails.this,++MainActivity.numAlert ,intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            LocalDateTime midnight = LocalDate.now().atStartOfDay();
            ZonedDateTime zonedMidnight = midnight.atZone(ZoneId.systemDefault());
            Date dateMidnight = Date.from(zonedMidnight.toInstant());
            if(triggerDate.getTime()>= dateMidnight.getTime()){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerDate.getTime(), pendingIntent);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if(item.getItemId()== R.id.notification){
            String title = getIntent().getStringExtra("title");
            String startText="Your course starts today!";
            String endText="Your course ends today!";
            String start = getIntent().getStringExtra("start date");
            String end = getIntent().getStringExtra("end date");
            scheduleNotification(this,title,startText,dateParse(start));
            scheduleNotification(this,title,endText,dateParse(end));
        }
//        //if(item.getItemId()==R.id.savecourse){
//          //  Course course;
//
//            if(courseID==-1){
//              if(repository.getmAllCourses().isEmpty())courseID=1;
//              else courseID=repository.getmAllCourses().get(repository.getmAllCourses().size()-1).getCourseID()+1;
//              course=new Course(courseID,editTitle.getText().toString(), editStart.getText().toString(),editEnd.getText().toString(),selectedStatus,editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(),editInstructorName.getText().toString(),termid);
//              repository.updateCourse(course);
//              }
//            else {
//                course=new Course(courseID,editTitle.getText().toString(), editStart.getText().toString(),editEnd.getText().toString(),selectedStatus,editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(),editInstructorName.getText().toString(),termid);
//                repository.insertCourse(course);
//            }



     //   }
        if(item.getItemId()==R.id.deletecourse){
            Course course;
            if(repository.getmAssociatedAssessments(courseID).isEmpty()){
                course=repository.getCourse(courseID);
                repository.deleteCourse(course);
                finish();
            }
            else{
                Toast.makeText(this, "Course has an associated Assessment. Did not delete.", Toast.LENGTH_LONG).show();
            }
        }
        if(item.getItemId()==R.id.sharenote){
            Intent sendIntent=new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,editNote.getText().toString());
            sendIntent.putExtra(Intent.EXTRA_TITLE,"Note");
            sendIntent.setType("text/plain");

            Intent shareIntent=Intent.createChooser(sendIntent,null);
            startActivity(shareIntent);
            return true;

        }
        if(item.getItemId()==R.id.addassessment){
            Intent intent=new Intent(CourseDetails.this,AssessmentList.class);
            intent.putExtra("courseid",cID);
            startActivity(intent);
        }
        if(item.getItemId()== android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }
    @Override
    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView=findViewById(R.id.cdrecycler);
        repository=new Repository(getApplication());
        final AssessmentAdaptor assessmentAdaptor=new AssessmentAdaptor(this);
        recyclerView.setAdapter(assessmentAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdaptor.setAssessments(repository.getmAssociatedAssessments(courseID));
    }
}