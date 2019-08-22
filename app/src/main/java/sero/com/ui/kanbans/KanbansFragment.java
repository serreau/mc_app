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
    @BindView(R.id.public_kanban_text) TextView public_kanban_text;
    @BindView(R.id.private_kanban_text) TextView private_kanban_text;
    @BindView(R.id.public_kanban_layout) CardView public_kanban_layout;
    @BindView(R.id.private_kanban_layout) CardView private_kanban_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kanbans_fragment, container, false);
        ButterKnife.bind(this, view);

        public_kanban_text.setText("PUBLIQUE");
        private_kanban_text.setText("PRIVÉ");

        public_kanban_layout.setOnClickListener(v -> {
            SharedPreferencesHelper.setKanban("public", getContext());
            Navigation.findNavController(view).popBackStack();
        });

        private_kanban_layout.setOnClickListener(v -> {
            SharedPreferencesHelper.setKanban(SharedPreferencesHelper.getSp(getContext()).getString("login", "public"), getContext());
            Navigation.findNavController(view).popBackStack();
        });

        return  view;
    }

}
