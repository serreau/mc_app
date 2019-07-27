package sero.com.ui.fragment;

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

import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import sero.com.ui.viewmodel.JobViewModel;
import sero.com.data.entities.Job;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;

public class SearchFragment extends Fragment {
    JobViewModel jobviewmodel;

    FloatingActionButton action_button;
    TextInputEditText searchjob_input;

    RecyclerView recyclerView;
    private JobAdapter mAdapter;

    List<Job> arraylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        action_button = view.findViewById(R.id.action_button);
        searchjob_input = view.findViewById(R.id.textedit_home);

        arraylist = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_home);
        mAdapter = new JobAdapter(getContext(), arraylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        jobviewmodel = ViewModelProviders.of(getActivity()).get(JobViewModel.class);
        jobviewmodel.contains(searchjob_input.getText().toString()).observe(
                this, jobs -> mAdapter.setJobs(jobs));

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

        action_button.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                        R.id.action_searchFragment_to_createJobFragment
                ));

        return  view;
    }

}
