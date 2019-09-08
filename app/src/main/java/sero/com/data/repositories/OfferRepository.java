package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import sero.com.data.entities.Offer;
import sero.com.data.entities.User;

public interface OfferRepository {
    void insert(Offer offer);
    void delete(Offer offer);
    void update(Offer offer);
    LiveData<Offer> get(long id);
    LiveData<List<Offer>> get();

    LiveData<Offer> getBySenderAndJob(String login, long jobId);

    LiveData<List<Offer>> getByJob(Long jobId);
}
