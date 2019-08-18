package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.Uri;

import java.util.List;

import sero.com.data.dao.UserDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.User;
import sero.com.data.repositories.DashboardRepository;
import sero.com.data.repositories.UserRepository;

public class DashboardRepository_Room implements DashboardRepository {
    DB db;

    UserDao userdao;

    public DashboardRepository_Room(Context context){
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
    public User get(String id) {
        return userdao.get(id);
    }

    @Override
    public LiveData<List<User>> get() { return userdao.get(); }

    @Override
    public boolean exist(String phone, String password) {
        return userdao.exist(phone, password);
    }

    @Override
    public boolean exist(String phone) {
        return userdao.exist(phone);
    }

    @Override
    public void updateImage(String login, String image) {
        userdao.updateImage(login, image);
    }
}
