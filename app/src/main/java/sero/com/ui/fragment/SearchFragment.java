package sero.com.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.viewmodel.JobViewModel;
import sero.com.data.entities.Job;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;
import sero.com.util.LoginManager;

public class SearchFragment extends Fragment {
    JobViewModel jobviewmodel;

    @BindView(R.id.action_button) FloatingActionButton action_button;
    @BindView(R.id.textedit_home) TextInputEditText searchjob_input;

    @BindView(R.id.recycler_home) RecyclerView recyclerView;
    private JobAdapter mAdapter;

    List<Job> arraylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);

        arraylist = new ArrayList<>();
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
