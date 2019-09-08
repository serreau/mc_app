package sero.com.data.repositories.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import sero.com.data.dao.JobDao;
import sero.com.data.databases.DB;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;

public class JobRepository_Room implements JobRepository {
    DB db;
    JobDao jobDao;

    public JobRepository_Room(Context context){
        db = DB.getInstance(context);
        jobDao = db.jobDao();
    }

    @Override
    public void update(Job job) {
        jobDao.update(job);
    }

    @Override
    public void insert(Job job) {
        jobDao.insert(job);
    }

    @Override
    public void delete(Job job) {
        jobDao.delete(job);
    }

    @Override
    public LiveData<Job> get(long id) {
        return jobDao.get(id);
    }

    @Override
    public LiveData<List<Job>> get() { return jobDao.get(); }

    @Override
    public LiveData<List<Job>> getByStateSearch(String state, String search) {
        return jobDao.getByStateSearch(state, search);
    }

    @Override
    public LiveData<List<Job>> getByOwnerStateSearch(String owner, String state, String search) {
        return jobDao.getByOwnerStateSearch(owner, state, search);
    }

    @Override
    public LiveData<Boolean> isOwner(String login, long jobId) {
        return jobDao.isOwner( login, jobId);
    }

}
