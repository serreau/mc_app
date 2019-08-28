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
    private OfferViewModel viewModel;

    @BindView(R.id.actionButton)
    FloatingActionButton actionButton;
    @BindView(R.id.priceInput)
    TextInputEditText priceInput;

    Boolean hasOffer = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(OfferViewModel.class);
        ButterKnife.bind(this, view);

        viewModel.setJobId(getArguments().getLong("id", 0));

        viewModel.getOffer().observe(this, offer -> {
            if(offer == null)
                return;
            priceInput.setText(""+offer.getPrice());
            priceInput.setEnabled(false);
            hasOffer = true;
        });
        actionButton.setOnClickListener( (v) -> {
            if(!hasOffer) {
                viewModel.getJob().observe(this, job -> {
                    Offer offer = new Offer();
                    offer.setJob(job.getId());
                    offer.setPrice(Long.parseLong(priceInput.getText().toString()));
                    offer.setSender(SharedPreferencesHelper.getlogin(getContext()));
                    offer.setReceiver(job.getOwner());
                    offer.setState("SEND");
                    viewModel.createOffer(offer);
                });
            }
        });

        return view;
    }


}
