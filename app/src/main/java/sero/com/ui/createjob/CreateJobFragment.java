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
import sero.com.util.State;

public class CreateJobFragment extends Fragment {
    CreateJobViewModel createviewmodel;

    @BindView(R.id.action_button) FloatingActionButton action_button;

    @BindView(R.id.job_1_input) TextInputEditText owner;
    @BindView(R.id.job_2_input) TextInputEditText name;

    @BindView(R.id.job_1_layout) TextInputLayout owner_inputlayout;
    @BindView(R.id.job_2_layout) TextInputLayout name_inputlayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        ButterKnife.bind(this, view);

        createviewmodel = ViewModelProviders.of(getActivity()).get(CreateJobViewModel.class);

        owner.setOnFocusChangeListener(
                (v,hasFocus) -> owner_inputlayout.setError(null));
        name.setOnFocusChangeListener(
                (v,hasFocus) ->  name_inputlayout.setError(null));

        action_button.setOnClickListener( (v) -> {
            Job newjob = new Job();
            newjob.setOwner(owner.getText().toString());
            newjob.setName(name.getText().toString());
            newjob.setState(State.TODO.toString());

            if(!newjob.getOwner().isEmpty() && !newjob.getName().isEmpty()) {
                createviewmodel.insert(newjob);
                Navigation.findNavController(view).popBackStack();
            }

            if (newjob.getOwner().isEmpty())
                owner_inputlayout.setError(getString(R.string.fieldrequired_error));
            if (newjob.getName().isEmpty())
                name_inputlayout.setError(getString(R.string.fieldrequired_error));

        });

        return view;
    }

}
