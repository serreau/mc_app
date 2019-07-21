package sero.com.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface JobRepository<Job> extends Repository<Job>{
    void insert(Job object);
    void delete(Job object);
    void update(Job object);
    LiveData<Job> get(long id);
    LiveData<List<Job>> get();

    LiveData<List<Job>> contains(String title);
}
