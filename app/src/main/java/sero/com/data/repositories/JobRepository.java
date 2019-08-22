package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import sero.com.data.entities.Job;
import sero.com.util.State;

public interface JobRepository{
    void insert(Job job);
    void delete(Job job);
    void update(Job job);
    LiveData<Job> get(long id);
    LiveData<List<Job>> get();

    LiveData<List<Job>> getByStateSearch(String toString, String search);
    LiveData<List<Job>> getByOwnerStateSearch(String owner, String toString, String search);
}
