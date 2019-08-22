package sero.com.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sero.com.data.entities.Job;
import sero.com.data.entities.User;

@Dao
public interface JobDao {
    @Insert
    void insert(Job job);

    @Delete
    void delete(Job job);

    @Update
    void update(Job job);

    @Query("SELECT * FROM Job WHERE id = (:id)")
    LiveData<Job> get(long id);

    @Query("SELECT * FROM Job")
    LiveData<List<Job>> get();

    @Query("SELECT * FROM Job WHERE state = :state AND owner || ' ' || name LIKE '%' || :search || '%'")
    LiveData<List<Job>> getByStateSearch(String state, String search);

    @Query("SELECT * FROM Job WHERE owner = :owner AND state = :state AND name LIKE '%' || :search || '%'")
    LiveData<List<Job>> getByOwnerStateSearch(String owner, String state, String search);
}
