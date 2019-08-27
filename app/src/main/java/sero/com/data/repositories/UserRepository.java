package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import sero.com.data.entities.Job;
import sero.com.data.entities.User;

public interface UserRepository{
    void insert(User user);
    void delete(User user);
    void update(User user);
    LiveData<User> get(String id);
    LiveData<List<User>> get();
    LiveData<List<User>> get(String[] logins);

    boolean exist(String phone, String password);
    boolean exist(String phone);
    LiveData<String> getFirstname(String phone);

}
