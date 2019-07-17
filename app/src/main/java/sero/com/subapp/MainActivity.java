package sero.com.subapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sero.com.Factories.RepositoryFactory;
import sero.com.repositories.JobRepository;
import sero.com.entities.Job;

public class MainActivity extends Activity {
    JobRepository jobrepository;

    TextInputEditText searchjob_input;
    FloatingActionButton addjob_button;
    ListView listview;

    ArrayAdapter<Job> arrayadapter;
    ArrayList<Job> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobrepository = RepositoryFactory.getJobRepository(RepositoryFactory.Entities.JOB , this.getApplicationContext());

        searchjob_input = findViewById(R.id.textedit_home);
        addjob_button = findViewById(R.id.button_home);
        listview = findViewById(R.id.list_home);

        arraylist = new ArrayList<>();
        arrayadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraylist);

        listview.setAdapter(arrayadapter);


        searchjob_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                arraylist.removeAll(arraylist);
                arrayadapter.addAll(jobrepository.contains(editable.toString()));
            }
        });

        addjob_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Job n = new Job();
                n.setName(searchjob_input.getText().toString());
                jobrepository.insert(n);
                arrayadapter.add(n);
                Toast.makeText( getApplicationContext(), "Nouveau Job créé", Toast.LENGTH_SHORT ).show();
            }
        });

    }
}
