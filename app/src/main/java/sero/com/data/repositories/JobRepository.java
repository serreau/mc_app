package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface JobRepository<Job> extends Repository<Job>{
    void insert(Job job);
    void delete(Job job);
    void update(Job job);
    LiveData<Job> get(long id);
    LiveData<List<Job>> get();

    LiveData<List<Job>> contains(String title);
}
