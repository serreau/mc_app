package sero.com.repositories.impl;

import android.content.Context;

import java.util.List;

import sero.com.dao.JobDao;
import sero.com.repositories.JobRepository;
import sero.com.databases.DB;
import sero.com.entities.Job;

public class JobRepository_Room implements JobRepository<Job> {
    DB db;
    JobDao jobdao;

    public JobRepository_Room(Context context){
        db = DB.getInstance(context);
        jobdao = db.jobDao();
    }

    @Override
    public void update(Job object) {
        jobdao.update(object);
    }

    @Override
    public void insert(Job object) {
        jobdao.insert(object);
    }

    @Override
    public void delete(Job object) {
        jobdao.delete(object);
    }

    @Override
    public Job get(long id) {
        return jobdao.get(id);
    }

    @Override
    public List<Job> contains(String id) {
        return jobdao.contains(id);
    }
    @Override
    public List<Job> getAll() {
        return jobdao.getAll();
    }
}
