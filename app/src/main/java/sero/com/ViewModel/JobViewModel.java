package sero.com.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import sero.com.Factories.RepositoryFactory;
import sero.com.entities.Job;
import sero.com.repositories.JobRepository;

public class JobViewModel extends AndroidViewModel {

    private final JobRepository jobrepository;
    MutableLiveData<String> search;
    LiveData<List<Job>> jobsbysearch;


    public JobViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        jobrepository = RepositoryFactory.getJobRepository(RepositoryFactory.Entities.JOB, application);

        search = new MutableLiveData();
        jobsbysearch = Transformations.switchMap(search, search -> {
            return jobrepository.contains(search);
        });
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

    public LiveData<List<Job>> get(){
        return jobrepository.get();
    }

    public LiveData<List<Job>> contains(String inputsearch){ return jobsbysearch; }

    public void setSearch(String text){ search.setValue(text); }

}
