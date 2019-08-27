package sero.com.ui.createjob;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.data.entities.Job;
import sero.com.ui.R;
import sero.com.util.SharedPreferencesHelper;
import sero.com.util.State;

public class CreateJobFragment extends Fragment {
    CreateJobViewModel createviewmodel;

    @BindView(R.id.action_button)
    FloatingActionButton action_button;

    @BindView(R.id.jobowner_input)
    TextInputEditText jobowner_input;
    @BindView(R.id.jobname_input)
    TextInputEditText jobname_input;

    @BindView(R.id.jobowner_layout)
    TextInputLayout jobowner_layout;
    @BindView(R.id.jobname_layout)
    TextInputLayout jobname_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        ButterKnife.bind(this, view);

        createviewmodel = ViewModelProviders.of(getActivity()).get(CreateJobViewModel.class);

        createviewmodel.getFirstname().observe( this, s -> jobowner_input.setText(s));

        jobowner_input.setOnFocusChangeListener(
                (v,hasFocus) -> jobowner_layout.setError(null));
        jobname_input.setOnFocusChangeListener(
                (v,hasFocus) ->  jobname_layout.setError(null));

        action_button.setOnClickListener( (v) -> {
            Job newjob = new Job();
            newjob.setOwner(SharedPreferencesHelper.getSp(getContext()).getString("login", "error"));
            newjob.setName(jobname_input.getText().toString());
            newjob.setState(State.TODO.toString());

            if(!newjob.getOwner().isEmpty() && !newjob.getName().isEmpty()) {
                createviewmodel.insert(newjob);
                Navigation.findNavController(view).popBackStack();
            }

            if (newjob.getOwner().isEmpty())
                jobowner_layout.setError(getString(R.string.fieldrequired_error));
            if (newjob.getName().isEmpty())
                jobname_layout.setError(getString(R.string.fieldrequired_error));

        });

        return view;
    }

}
