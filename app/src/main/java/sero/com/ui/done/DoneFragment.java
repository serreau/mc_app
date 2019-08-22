package sero.com.ui.done;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;

public class DoneFragment extends Fragment{
    DoneViewModel searchviewmodel;

    @BindView(R.id.textedit_home) TextInputEditText searchjob_input;

    @BindView(R.id.recycler_home) RecyclerView recyclerView;

    private JobAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.done_fragment, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new JobAdapter(new ArrayList<>(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        searchviewmodel = ViewModelProviders.of(getActivity()).get(DoneViewModel.class);
        searchviewmodel.searchResult(searchjob_input.getText().toString()).observe(
                this, jobs -> mAdapter.setJobs(jobs));

        searchjob_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                searchviewmodel.setSearch(editable.toString());
            }
        });

        return  view;
    }

}
