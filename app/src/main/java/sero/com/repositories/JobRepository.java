package sero.com.repositories;

import java.util.List;

public interface JobRepository<Job> extends Repository<Job>{
    void insert(Job object);
    void delete(Job object);
    void update(Job object);
    Job get(long id);
    List<Job> contains(String id);
    List<Job> getAll();
}
