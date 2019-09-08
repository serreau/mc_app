package sero.com.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import sero.com.data.entities.Job;
import sero.com.data.entities.Offer;
import sero.com.data.entities.User;
import sero.com.ui.R;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {
    public List<Offer> offers;
    public List<User> users;


    public OffersAdapter(List<Offer> offers, List<User> users) {
        this.offers = offers;
        this.users = users;
    }

    @Override
    public OffersAdapter.OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        ButterKnife.bind(this, view);


        view.setOnClickListener( v -> { });
        OfferViewHolder offerViewHolder = new OfferViewHolder(view);

        return offerViewHolder;
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        Offer offer = offers.get(position);
        holder.setOffer(offer);
        holder.setUser(getUserByOffer(offer));
        holder.setText();
    }

    private User getUserByOffer(Offer offer) {
        ListIterator<User> it = users.listIterator();
        while (it.hasNext()){
            User user = it.next();
            if(offer.getSender().equals(user.getPhone()))
                return user;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        private Offer offer;
        private User user;

        public OfferViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.nameCard);
        }

        public void setOffer(Offer offer) {
            this.offer = offer;
        }

        public Offer getOffer() {
            return this.offer;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setText(){
            if(user == null || offer == null  ) {
                text.setText("Informations manquantes");
                return;
            }
            text.setText(user.getFirstname()+" propose "+offer.getPrice()+"€");
        }
    }


}