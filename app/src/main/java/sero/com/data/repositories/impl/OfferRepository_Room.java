package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import sero.com.data.dao.OfferDao;
import sero.com.data.dao.UserDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.Offer;
import sero.com.data.entities.User;
import sero.com.data.repositories.OfferRepository;
import sero.com.data.repositories.UserRepository;

public class OfferRepository_Room implements OfferRepository {
    DB db;
    OfferDao offerDao;

    public OfferRepository_Room(Context context){
        db = DB.getInstance(context);
        offerDao = db.offerDao();
    }

    @Override
    public void insert(Offer offer) {
        offerDao.insert(offer);
    }

    @Override
    public void delete(Offer offer) {
        offerDao.delete(offer);
    }

    @Override
    public void update(Offer offer) {
        offerDao.update(offer);
    }

    @Override
    public LiveData<Offer> get(long id) {
        return offerDao.get(id);
    }

    @Override
    public LiveData<List<Offer>> get() {
        return offerDao.get();
    }

    @Override
    public LiveData<Offer> getBySenderAndJob(String login, long jobId) {
        return offerDao.getBySenderAndJob(login, jobId);
    }
}
