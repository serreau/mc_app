package sero.com.ui.fragment;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import sero.com.ui.viewmodel.JobViewModel;
import sero.com.data.entities.Job;
import sero.com.ui.R;
import sero.com.ui.adapter.JobAdapter;

public class DetailsFragment extends Fragment {
        TextView title_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailsjob_fragment, container, false);

        title_text = view.findViewById(R.id.title_card);
        title_text.setText(getArguments().getString("title", "Une erreur est survenue"));

        return  view;
    }

}
