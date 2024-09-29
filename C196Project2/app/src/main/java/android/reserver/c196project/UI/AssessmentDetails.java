package android.reserver.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;


public class AssessmentDetails extends AppCompatActivity {
    int assessmentID;
    private Repository repository;
    EditText editTitle;
    EditText editInfo;
    EditText editEnd;
    EditText editStart;
    Spinner editType;
    private String assType;
    int id;
    int courseID;
    String start;
    String title;
    String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        editStart=findViewById(R.id.startDate);
        editTitle=findViewById(R.id.assessmenttitletext);
        editEnd=findViewById(R.id.endDateTextAssess);
        editType=findViewById(R.id.assessType);
        id=getIntent().getIntExtra("id",courseID);
        title=getIntent().getStringExtra("title");
        start=getIntent().getStringExtra("start date");
        end=getIntent().getStringExtra("end date");
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        repository=new Repository(getApplication());
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_assessment_details,menu);



        final String[] typeOptions={"Objective","Performance"};
        ArrayAdapter<CharSequence>arrayAdapter=ArrayAdapter.createFromResource(this,R.array.assessType, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editType.setAdapter(arrayAdapter);
        Intent myIntent=getIntent();
        courseID=myIntent.getIntExtra("courseid",courseID);
        assessmentID=myIntent.getIntExtra("id",assessmentID);
        editType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assType=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Assessment assessment;

        if(assessmentID>0){
            assessment=repository.getAssessment(assessmentID);
            int position=arrayAdapter.getPosition(assessment.getAssessmentType());
            editType.setSelection(position);
        }
        else if(repository.getmAllAssessments().isEmpty()){
            assessment=new Assessment(assessmentID,title,start,end,assType,courseID);
        }


        else{
            assessmentID=repository.getmAllAssessments().get(repository.getmAllAssessments().size()-1).getAssessmentID()+1;
            assessment=new Assessment(assessmentID,title,start,end,assType,courseID);
        }

        Button button=findViewById(R.id.saveButtonAss);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentDetails.this,CourseDetails.class);
                String title=editTitle.getText().toString();
                String start=editStart.getText().toString();
                String end=editEnd.getText().toString();
                String status=assType;
                Course prevCourse = repository.getCourse(courseID);
                intent.putExtra("id",prevCourse.getCourseID());
                intent.putExtra("title",prevCourse.getCourseTitle());
                intent.putExtra("start date",prevCourse.getStartDate());
                intent.putExtra("end date",prevCourse.getEndDate());
                intent.putExtra("Selected Status",prevCourse.getCourseStatus());
                intent.putExtra("instructor name",prevCourse.getInstructorName());
                intent.putExtra("instructor phone number",prevCourse.getInstructorPhoneNum());
                intent.putExtra("instructor email",prevCourse.getInstructorEmail());
                intent.putExtra("termid",prevCourse.getTermID());
                intent.putExtra("destScreen","CourseDetails");
                Assessment assessment=new Assessment(assessmentID,title,start,end,status,courseID);
                repository.insertAssessment(assessment);
                setResult(RESULT_OK, intent);
                finish();
                //startActivity(intent);
            }

        });
        Button cancelButton=findViewById(R.id.cancelButtonAss);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            LocalDateTime midnight = LocalDate.now().atStartOfDay();
            ZonedDateTime zonedMidnight = midnight.atZone(ZoneId.systemDefault());
            Date dateMidnight = Date.from(zonedMidnight.toInstant());
            if (triggerDate.getTime() >= dateMidnight.getTime()) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerDate.getTime(), pendingIntent);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== R.id.notify){
            String title = getIntent().getStringExtra("title");
            String startText="Your assessment starts today!";
            String endText="Your assessment ends today!";
            String start = getIntent().getStringExtra("start date");
            String end = getIntent().getStringExtra("end date");
            scheduleNotification(this,title,startText,dateParse(start));
            scheduleNotification(this,title,endText,dateParse(end));
        }
        if(item.getItemId()== R.id.deleteassessment){
            Assessment assessment;
            assessment=repository.getAssessment(assessmentID);
            repository.deleteAssessment(assessment);
            //setResult(RESULT_OK, resultIntent);
            finish();
        }
        if(item.getItemId()== android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }


}