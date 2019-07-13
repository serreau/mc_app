package sero.com.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sero.com.entities.Nanos;

@Dao
public interface NanosDao {
    @Query("SELECT * FROM Nanos")
    List<Nanos> getAll();

    @Insert
    void insert(Nanos... nanos);

    @Delete
    void delete(Nanos... nanos);

    @Update
    void update(Nanos... nanos);
}
