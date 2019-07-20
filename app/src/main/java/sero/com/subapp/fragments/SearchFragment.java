package sero.com.subapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sero.com.ViewModel.JobViewModel;
import sero.com.entities.Job;
import sero.com.subapp.R;
import sero.com.subapp.adapters.JobAdapter;

public class SearchFragment extends Fragment {
    JobViewModel jobviewmodel;

    TextInputEditText searchjob_input;
    FloatingActionButton addjob_button;
    RecyclerView recyclerView;
    private JobAdapter mAdapter;

    List<Job> arraylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        searchjob_input = view.findViewById(R.id.textedit_home);
        addjob_button = view.findViewById(R.id.button_home);

        arraylist = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_home);
        mAdapter = new JobAdapter(getContext(), arraylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        jobviewmodel = ViewModelProviders.of(this).get(JobViewModel.class);
        jobviewmodel.contains(searchjob_input.getText().toString()).observe(this, jobs -> {
            mAdapter.setJobs(jobs);
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
            Toast.makeText( getContext(), "Nouveau Job créé", Toast.LENGTH_SHORT ).show();
        });
        return  view;
    }
}
