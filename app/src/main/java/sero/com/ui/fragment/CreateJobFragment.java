package sero.com.ui.fragment;

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
import androidx.navigation.Navigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.viewmodel.JobViewModel;
import sero.com.data.entities.Job;
import sero.com.ui.R;

public class CreateJobFragment extends Fragment {
    JobViewModel jobviewmodel;

    @BindView(R.id.action_button) FloatingActionButton action_button;

    @BindView(R.id.job_1_input) TextInputEditText title1;
    @BindView(R.id.job_2_input) TextInputEditText title2;

    @BindView(R.id.job_1_layout) TextInputLayout title1_inputlayout;
    @BindView(R.id.job_2_layout) TextInputLayout title2_inputlayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createjob_fragment, container, false);
        ButterKnife.bind(this, view);

        jobviewmodel = ViewModelProviders.of(getActivity()).get(JobViewModel.class);

        title1.setOnFocusChangeListener(
                (v,hasFocus) -> title1_inputlayout.setError(null));
        title2.setOnFocusChangeListener(
                (v,hasFocus) ->  title2_inputlayout.setError(null));

        action_button.setOnClickListener( (v) -> {
            Job newjob = new Job();
            newjob.setTitle1(title1.getText().toString());
            newjob.setTitle2(title2.getText().toString());

            if(!newjob.getTitle1().isEmpty() && !newjob.getTitle2().isEmpty()) {
                jobviewmodel.insert(newjob);
                Navigation.findNavController(view).popBackStack();
            }

            if (newjob.getTitle1().isEmpty())
                title1_inputlayout.setError(getString(R.string.fieldrequired_error));
            if (newjob.getTitle2().isEmpty())
                title2_inputlayout.setError(getString(R.string.fieldrequired_error));

        });

        return view;
    }

}
