package sero.com.ui.kanbans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.util.SharedPreferencesHelper;

public class KanbansFragment extends Fragment {
    @BindView(R.id.publicKanbanTextView) TextView publicKanbanTextView;
    @BindView(R.id.privateKanbanTextView) TextView privateKanbanTextView;
    @BindView(R.id.publicKanbanLayout) CardView publicKanbanLayout;
    @BindView(R.id.privateKanbanLayout) CardView privateKanbanLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kanbans_fragment, container, false);
        ButterKnife.bind(this, view);

        publicKanbanTextView.setText(getString(R.string.publicKanbanName));
        privateKanbanTextView.setText(getString(R.string.privateKanbanName));

        publicKanbanLayout.setOnClickListener(v -> {
            SharedPreferencesHelper.setKanban("public", getContext());
            Navigation.findNavController(view).popBackStack();
        });

        privateKanbanLayout.setOnClickListener(v -> {
            SharedPreferencesHelper.setKanban(SharedPreferencesHelper.getlogin(getContext()), getContext());
            Navigation.findNavController(view).popBackStack();
        });

        return  view;
    }

}
