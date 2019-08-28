package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import sero.com.data.dao.UserDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;

public class UserRepository_Room implements UserRepository {
    DB db;
    UserDao userDao;

    public UserRepository_Room(Context context){
        db = DB.getInstance(context);
        userDao = db.userDao();
    }

    @Override
    public void update(User job) {
        userDao.update(job);
    }

    @Override
    public void insert(User job) {
        userDao.insert(job);
    }

    @Override
    public void delete(User job) {
        userDao.delete(job);
    }

    @Override
    public LiveData<User> get(String id) {
        return userDao.get(id);
    }

    @Override
    public LiveData<List<User>> get() { return userDao.get(); }

    @Override
    public boolean exist(String phone, String password) {
        return userDao.exist(phone, password);
    }

    @Override
    public boolean exist(String phone) {
        return userDao.exist(phone);
    }

    @Override
    public LiveData<String> getFirstname(String phone) {
        return userDao.getFirstname(phone);
    }

    @Override
    public LiveData<List<User>> get(String[] logins) {
        return userDao.get(logins);
    }

}
