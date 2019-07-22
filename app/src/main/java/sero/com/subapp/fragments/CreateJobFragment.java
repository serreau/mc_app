package sero.com.subapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import java.util.Calendar;

import sero.com.ViewModel.JobViewModel;
import sero.com.entities.Job;
import sero.com.subapp.R;
import sero.com.subapp.dialogs.DatePickerFragment;

public class CreateJobFragment extends Fragment {
    JobViewModel jobviewmodel;

    FloatingActionButton action_button;

    TextInputEditText title;
    TextInputEditText description;
    TextInputEditText startdate;

    TextInputLayout title_inputlayout;
    TextInputLayout description_inputlayout;
    TextInputLayout startdate_inputlayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        action_button = view.findViewById(R.id.action_button);

        title = view.findViewById(R.id.jobtitle_input);
        description = view.findViewById(R.id.jobdescription_input);
        startdate = view.findViewById(R.id.jobstartdate_input);

        title_inputlayout = view.findViewById(R.id.jobtitle_layout);
        description_inputlayout = view.findViewById(R.id.jobdescription_layout);
        startdate_inputlayout = view.findViewById(R.id.jobdate_layout);

        jobviewmodel = ViewModelProviders.of(getActivity()).get(JobViewModel.class);


        title.setOnFocusChangeListener(
                (v,hasFocus) -> title_inputlayout.setError(null));
        description.setOnFocusChangeListener(
                (v,hasFocus) ->  description_inputlayout.setError(null));
        startdate.setOnTouchListener((view1, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                new DatePickerFragment().show(getFragmentManager(), "dialog");
                return true;
            }
            return false;
        });

        action_button.setOnClickListener( (v) -> {

             Calendar c = Calendar.getInstance();

            Job newjob = new Job();
            newjob.setTitle(title.getText().toString());
            newjob.setDescription(description.getText().toString());
            newjob.setStart(startdate.getText().toString());

            //if(newjob.getStart().isEmpty())
              //  startdate.setText(c.da+"/"+c.MONTH+"/"+c.YEAR);

            if(!newjob.getTitle().isEmpty() && !newjob.getDescription().isEmpty()) {
                jobviewmodel.insert(newjob);
                Navigation.findNavController(view).popBackStack();
            }

            if (newjob.getTitle().isEmpty())
                title_inputlayout.setError("Ce champs est obligatoire");
            if (newjob.getDescription().isEmpty())
                description_inputlayout.setError("Ce champs est obligatoire");

        });
        return view;
    }
}
