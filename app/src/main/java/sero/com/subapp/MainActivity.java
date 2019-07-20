package sero.com.subapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sero.com.ViewModel.JobViewModel;
import sero.com.entities.Job;

public class MainActivity extends AppCompatActivity {
    JobViewModel jobviewmodel;


    TextInputEditText searchjob_input;
    FloatingActionButton addjob_button;
    ListView listview;

    ArrayAdapter<Job> arrayadapter;
    ArrayList<Job> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchjob_input = findViewById(R.id.textedit_home);
        addjob_button = findViewById(R.id.button_home);
        listview = findViewById(R.id.list_home);

        arraylist = new ArrayList<>();
        arrayadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraylist);
        listview.setAdapter(arrayadapter);

        jobviewmodel = ViewModelProviders.of(this).get(JobViewModel.class);
        jobviewmodel.contains(searchjob_input.getText().toString()).observe(this, jobs -> {
                arrayadapter.clear();
                arrayadapter.addAll(jobs);
        });

        searchjob_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                jobviewmodel.setSearch(editable.toString());
            }
        });

        addjob_button.setOnClickListener(v -> {
            Job job = new Job();
            job.setName(searchjob_input.getText().toString());
            jobviewmodel.insert(job);
            Toast.makeText( getApplicationContext(), "Nouveau Job créé", Toast.LENGTH_SHORT ).show();
        });

    }

}
