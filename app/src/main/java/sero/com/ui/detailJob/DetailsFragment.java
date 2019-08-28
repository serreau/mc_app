package sero.com.ui.detailJob;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;

public class DetailsFragment extends Fragment {
    DetailViewModel viewModel;

    @BindView(R.id.nameCard) TextView nameCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailsjob_fragment, container, false);
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        viewModel.setJobId(getArguments().getLong("id", 0));

        viewModel.getJob().observe(this, job -> {
           viewModel.getUser(job.getOwner()).observe(this, user -> {
               nameCard.setText(user.getFirstname()+" "+getString(R.string.wordConnector)+" "+job.getName());
           });
        });


        return  view;
    }

}
