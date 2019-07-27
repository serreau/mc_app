package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import sero.com.data.dao.JobDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;

public class JobRepository_Room implements JobRepository<Job> {
    DB db;
    JobDao jobdao;

    public JobRepository_Room(Context context){
        db = DB.getInstance(context);
        jobdao = db.jobDao();
    }

    @Override
    public void update(Job job) {
        jobdao.update(job);
    }

    @Override
    public void insert(Job job) {
        jobdao.insert(job);
    }

    @Override
    public void delete(Job job) {
        jobdao.delete(job);
    }

    @Override
    public LiveData<Job> get(long id) {
        return jobdao.get(id);
    }

    @Override
    public LiveData<List<Job>> get() { return jobdao.get(); }

    @Override
    public LiveData<List<Job>> contains(String search) {
        return jobdao.contains(search);
    }

}
