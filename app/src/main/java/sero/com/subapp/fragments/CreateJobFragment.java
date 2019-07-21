package sero.com.subapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import sero.com.ViewModel.JobViewModel;
import sero.com.entities.Job;
import sero.com.subapp.R;

public class CreateJobFragment extends Fragment {
    JobViewModel jobviewmodel;

    FloatingActionButton action_button;

    TextInputEditText title;
    TextInputEditText description;
    TextInputEditText startdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        action_button = view.findViewById(R.id.action_button);

        title = view.findViewById(R.id.jobtitle_input);
        description = view.findViewById(R.id.jobdescription_input);
        startdate = view.findViewById(R.id.jobstartdate_input);

        jobviewmodel = ViewModelProviders.of(getActivity()).get(JobViewModel.class);

        action_button.setOnClickListener( v -> {
            Job newjob = new Job();
            newjob.setTitle(title.getText().toString());
            newjob.setDescription(description.getText().toString());
            newjob.setStart(startdate.getText().toString());
            jobviewmodel.insert(newjob);
            Navigation.findNavController(view).popBackStack();
        });
        return view;
    }
}
