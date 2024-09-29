package android.reserver.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.entities.Term;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        numAlert = prefs.getInt("notificationID", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.EnterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TermList.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.addterm){
            Term term=new Term(0,"Summer 2024","September 1 2024","September 30 2024 ");
        }return true;
    }
}