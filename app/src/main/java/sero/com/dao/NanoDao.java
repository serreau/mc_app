package sero.com.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sero.com.entities.Nano;

@Dao
public interface NanoDao {
    @Insert
    void insert(Nano nanos);

    @Delete
    void delete(Nano nanos);

    @Update
    void update(Nano nanos);

    @Query("SELECT * FROM Nano WHERE (:id)")
    Nano get(long id);

    @Query("SELECT * FROM Nano")
    List<Nano> getAll();
}
