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
    CreateJobViewModel createViewModel;

    @BindView(R.id.actionButton)
    FloatingActionButton actionButton;

    @BindView(R.id.jobOwnerInput)
    TextInputEditText jobOwnerInput;
    @BindView(R.id.jobNameInput)
    TextInputEditText jobNameInput;

    @BindView(R.id.jobOwnerLayout)
    TextInputLayout jobOwnerLayout;
    @BindView(R.id.jobNameLayout)
    TextInputLayout jobNameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        ButterKnife.bind(this, view);

        createViewModel = ViewModelProviders.of(getActivity()).get(CreateJobViewModel.class);

        createViewModel.getFirstname().observe( this, s -> jobOwnerInput.setText(s));

        jobOwnerInput.setOnFocusChangeListener(
                (v,hasFocus) -> jobOwnerLayout.setError(null));
        jobNameInput.setOnFocusChangeListener(
                (v,hasFocus) ->  jobNameLayout.setError(null));

        actionButton.setOnClickListener( (v) -> {
            Job newjob = new Job();
            newjob.setOwner(SharedPreferencesHelper.getlogin(getContext()));
            newjob.setName(jobNameInput.getText().toString());
            newjob.setState(State.TODO.toString());

            if(!newjob.getOwner().isEmpty() && !newjob.getName().isEmpty()) {
                createViewModel.insert(newjob);
                Navigation.findNavController(view).popBackStack();
            }

            if (newjob.getOwner().isEmpty())
                jobOwnerLayout.setError(getString(R.string.fieldrequiredError));
            if (newjob.getName().isEmpty())
                jobNameLayout.setError(getString(R.string.fieldrequiredError));

        });

        return view;
    }

}
