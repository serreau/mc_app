package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import sero.com.data.entities.Job;

public interface UserRepository<User>{
    void insert(User user);
    void delete(User user);
    void update(User user);
    LiveData<User> get(long id);
    LiveData<List<User>> get();

    boolean exist(long phone, String password);
    boolean exist(long phone);
}
