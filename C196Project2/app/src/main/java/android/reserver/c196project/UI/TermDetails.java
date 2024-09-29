package android.reserver.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Assessment;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Term;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

public class TermDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editClass;
    Repository repository;
    int termID;
    RecyclerView recyclerView;
    String channelID = "c196";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_term_details);
        editTitle = findViewById(R.id.titleText);
        editStart = findViewById(R.id.termSD);
        editEnd = findViewById(R.id.termED);
        String title = getIntent().getStringExtra("title");
        String start = getIntent().getStringExtra("start date");
        String end = getIntent().getStringExtra("end date");
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        Intent termIntent = getIntent();
        termID = termIntent.getIntExtra("id", 0);
        createNotificationChannel(this);

    }
    public Date dateParse(String time){
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy",Locale.US);

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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(TermDetails.this,++MainActivity.numAlert ,intent, PendingIntent.FLAG_IMMUTABLE);
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



    

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_details, menu);
        Button button = findViewById(R.id.saveButton);
        Button cancelbutton=findViewById(R.id.cancelbuttonterm);
        editTitle = findViewById(R.id.titleText);
        editStart = findViewById(R.id.termSD);
        editEnd = findViewById(R.id.termED);


        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermDetails.this, TermList.class);
                String title= editTitle.getText().toString();
                String start=editStart.getText().toString();
                String end=editEnd.getText().toString();
                Term term=new Term(termID,title,start,end);
                repository.insertTerm(term);
                finish();

            }
        });
        recyclerView = findViewById(R.id.tdrecycler);
        repository = new Repository(getApplication());
        final CourseAdaptor courseAdaptor = new CourseAdaptor(this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdaptor.setCourses(repository.getmAssociatedCourses(termID));
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addclass){
           Intent intent= new Intent(TermDetails.this,CourseList.class);
           intent.putExtra("termid",termID);
           startActivity(intent);
        }
        if(item.getItemId()== R.id.notification){
            String title = getIntent().getStringExtra("title");
            String startText="Your term starts today!";
            String endText="Your term ends today!";
            String start = getIntent().getStringExtra("start date");
            String end = getIntent().getStringExtra("end date");
            scheduleNotification(this,title,startText,dateParse(start));
            scheduleNotification(this,title,endText,dateParse(end));
        }

//        if (item.getItemId() == R.id.saveterm) {
//            Term term;
//            if (termID == -1) {
//                if (repository.getmAllTerms().isEmpty()) termID = 1;
//                else
//                    termID = repository.getmAllTerms().get(repository.getmAllTerms().size() - 1).getTermID() + 1;
//                term = new Term(termID, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
//                repository.insertTerm(term);
//
//            } else {
//                term = new Term(termID, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
//                repository.updateTerm(term);
//
//
//            }







        //}
        if(item.getItemId()==R.id.deleteterm){
            Term term;
            if(repository.getmAssociatedCourses(termID).isEmpty()){
                term=repository.getmAssociatedTerms(termID);
                repository.deleteTerm(term);
                finish();
            }
            else{
                Toast.makeText(TermDetails.this,"Term has a course Associated. Did not delete.",Toast.LENGTH_LONG).show();
            }
        }
        if(item.getItemId()== android.R.id.home){
            this.finish();
            return true;
        }
        return true;

    }

    private void createNotificationChannel(Context context){
        CharSequence name= "mychanname";
        String description="channeldescription";
        int importance= NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel=new NotificationChannel(channelID,name,importance);
        channel.setDescription(description);
        NotificationManager notificationManager=context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.tdrecycler);
        repository = new Repository(getApplication());
        final CourseAdaptor courseAdaptor = new CourseAdaptor(this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdaptor.setCourses(repository.getmAssociatedCourses(termID));
    }
}






