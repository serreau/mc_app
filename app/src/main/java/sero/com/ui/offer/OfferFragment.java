package sero.com.ui.offer;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.data.entities.Job;
import sero.com.data.entities.Offer;
import sero.com.ui.R;
import sero.com.util.SharedPreferencesHelper;

public class OfferFragment extends Fragment{
    private OfferViewModel offerviewmodel;

    @BindView(R.id.action_button)
    FloatingActionButton action_button;
    @BindView(R.id.price_input)
    TextInputEditText price_input;

    Boolean hasoffer = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_fragment, container, false);
        offerviewmodel = ViewModelProviders.of(this).get(OfferViewModel.class);
        ButterKnife.bind(this, view);

        offerviewmodel.setJobId(getArguments().getLong("id", 0));

        offerviewmodel.getOffer().observe(this, offer -> {
            if(offer == null)
                return;
            price_input.setText(""+offer.getPrice());
            price_input.setEnabled(false);
            hasoffer = true;
        });
        action_button.setOnClickListener( (v) -> {
            if(hasoffer) {
                offerviewmodel.getJob().observe(this, job -> {
                    Offer offer = new Offer();
                    offer.setJob(job.getId());
                    offer.setPrice(Long.parseLong(price_input.getText().toString()));
                    offer.setSender(SharedPreferencesHelper.getSp(getContext()).getString("login", "error_login"));
                    offer.setReceiver(job.getOwner());
                    offer.setState("SEND");
                    offerviewmodel.createOffer(offer);
                });
            }
        });

        return view;
    }


}
