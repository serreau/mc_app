package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import sero.com.data.entities.User;

public interface DashboardRepository{
    void insert(User user);
    void delete(User user);
    void update(User user);
    User get(String id);
    LiveData<List<User>> get();

    boolean exist(String phone, String password);
    boolean exist(String phone);
}
