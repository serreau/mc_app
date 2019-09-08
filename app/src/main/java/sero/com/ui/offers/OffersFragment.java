package sero.com.ui.offers;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;
import sero.com.ui.adapter.OffersAdapter;

public class OffersFragment extends Fragment{
    private OffersViewModel viewModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private OffersAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        ButterKnife.bind(this, view);

        mAdapter = new OffersAdapter(new ArrayList<>(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        viewModel.setJobId(getArguments().getLong("id"));
        viewModel.getOffers().observe(this, offers -> {
            viewModel.getUsers().observe(this, users -> {
                mAdapter.setUsers(users);
            });
            mAdapter.setOffers(offers);
        });

        return view;
    }


}
