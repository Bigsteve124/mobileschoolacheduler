package android.reserver.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196project.R;
import android.reserver.c196project.database.Repository;
import android.reserver.c196project.entities.Course;
import android.reserver.c196project.entities.Term;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TermList extends AppCompatActivity {
private Repository repository;
int termID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new Repository(getApplication());
        List<Term> myTerms = repository.getmAllTerms();
        setContentView(R.layout.activity_term_list);
        FloatingActionButton fab = findViewById(R.id.AddTermButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                startActivity(intent);

            }
        });
        RecyclerView recyclerView= findViewById(R.id.termRecycler);
        repository=new Repository(getApplication());
        List<Term> allTerms=repository.getmAllTerms();
        final TermAdaptor termAdaptor= new TermAdaptor(this);
        recyclerView.setAdapter(termAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdaptor.setTerms(allTerms);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_list, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.addterm)
        {
            Term term = new Term(0,"Summer 2024", "06/01/2024", "08/31/2024");
            repository.insertTerm(term);
        }

        List<Term> allTerms= repository.getmAllTerms();
        RecyclerView recyclerView=findViewById(R.id.termRecycler);
        final TermAdaptor termAdaptor=new TermAdaptor(this);
        recyclerView.setAdapter(termAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdaptor.setTerms(allTerms);



        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;

        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();

        List<Term> allTerms= repository.getmAllTerms();
        RecyclerView recyclerView=findViewById(R.id.termRecycler);
        final TermAdaptor termAdaptor=new TermAdaptor(this);
        recyclerView.setAdapter(termAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdaptor.setTerms(allTerms);


    }
}
