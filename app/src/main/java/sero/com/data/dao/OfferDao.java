package sero.com.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sero.com.data.entities.Job;
import sero.com.data.entities.Offer;

@Dao
public interface OfferDao {
    @Insert
    void insert(Offer job);

    @Delete
    void delete(Offer job);

    @Update
    void update(Offer job);

    @Query("SELECT * FROM Offer WHERE id = (:id)")
    LiveData<Offer> get(long id);

    @Query("SELECT * FROM Offer")
    LiveData<List<Offer>> get();

    @Query("SELECT * FROM Offer WHERE sender = :login AND job = :jobid LIMIT 1")
    LiveData<Offer> getBySenderAndJob(String login, long jobid);
}
