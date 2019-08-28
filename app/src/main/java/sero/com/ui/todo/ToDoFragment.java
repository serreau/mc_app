package sero.com.ui.todo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;

public class ToDoFragment extends Fragment{
    View view;

    ToDoViewModel viewModel;

    @BindView(R.id.actionButton)
    FloatingActionButton actionButton;

    @BindView(R.id.searchInput) TextInputEditText searchJobInput;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private JobAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.todo_fragment, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new JobAdapter(new ArrayList<>(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        setListeners();


        viewModel = ViewModelProviders.of(getActivity()).get(ToDoViewModel.class);
        viewModel.searchResult().observe(
                this, jobs -> {
                    viewModel.getUsers().observe(this, users -> {
                        mAdapter.setUsers(users);
                    });
                    mAdapter.setJobs(jobs);
                }
        );

        return  view;
    }

    private void setListeners() {
        actionButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                        R.id.action_kanbanViewPagerFragment_to_createJobFragment
                ));
        actionButton.setOnLongClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPagerFragment_to_kanbansFragment);
            return true;
        });


        searchJobInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.setSearch(editable.toString());
            }
        });
    }

}
