package sero.com.ui.kanbans;

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

public class KanbansFragment extends Fragment {
    @BindView(R.id.public_kanban) TextView public_kanban;
    @BindView(R.id.private_kanban) TextView private_kanban;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kanbans_fragment, container, false);
        ButterKnife.bind(this, view);

        public_kanban.setText("PUBLIQUE");
        private_kanban.setText("PRIVÉE");

        return  view;
    }

}
