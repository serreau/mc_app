package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import sero.com.data.dao.UserDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;

public class UserRepository_Room implements UserRepository<User> {
    DB db;
    UserDao userdao;

    public UserRepository_Room(Context context){
        db = DB.getInstance(context);
        userdao = db.userDao();
    }

    @Override
    public void update(User job) {
        userdao.update(job);
    }

    @Override
    public void insert(User job) {
        userdao.insert(job);
    }

    @Override
    public void delete(User job) {
        userdao.delete(job);
    }

    @Override
    public LiveData<User> get(long id) {
        return userdao.get(id);
    }

    @Override
    public LiveData<List<User>> get() { return userdao.get(); }

    @Override
    public boolean exist(long phone, String password) {
        return userdao.exist(phone, password);
    }

    @Override
    public boolean exist(long phone) {
        return userdao.exist(phone);
    }
}
