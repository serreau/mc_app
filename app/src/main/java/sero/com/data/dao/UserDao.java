package sero.com.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.net.Uri;

import java.util.List;

import sero.com.data.entities.Job;
import sero.com.data.entities.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM User WHERE phone = (:phone)")
    LiveData<User> get(String phone);

    @Query("SELECT * FROM User")
    LiveData<List<User>> get();

    @Query("SELECT 1 FROM User WHERE phone = (:phone)" )
    boolean exist(String phone);

    @Query("SELECT 1 FROM User WHERE phone = (:phone) and password = (:password)" )
    boolean exist(String phone, String password);

    @Query("UPDATE User SET image = :image WHERE phone = :login")
    void updateImage(String login, String image);
}
