package sero.com.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;

import sero.com.Factories.RepositoryFactory;
import sero.com.entities.Job;
import sero.com.repositories.JobRepository;

public class JobViewModel extends AndroidViewModel {

    private final JobRepository jobrepository;
    Executor executor;

    public LiveData<List<Job>> alljobs;

    public JobViewModel(Application application) {
        super(application);
        jobrepository = RepositoryFactory.getJobRepository(RepositoryFactory.Entities.JOB, application);
        alljobs = this.getAll();
    }

    public void insert(Job job){
        jobrepository.insert(job);
    }

    public void delete(Job job){
        jobrepository.delete(job);
    }

    public void update(Job job){
        jobrepository.update(job);
    }

    public LiveData<Job> get(long id){
        return jobrepository.get(id);
    }

    public LiveData<List<Job>> getAll(){
        return jobrepository.getAll();
    }

    public LiveData<List<Job>> contains(String name){
        return jobrepository.contains(name);
    }
}
