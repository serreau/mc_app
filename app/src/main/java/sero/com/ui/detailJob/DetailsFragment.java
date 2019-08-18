package sero.com.ui.detailJob;

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
        @BindView(R.id.title_card) TextView title_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailsjob_fragment, container, false);
        ButterKnife.bind(this, view);

        title_text.setText(getArguments().getString("title", getString(R.string.error)));

        return  view;
    }

}
