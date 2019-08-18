package sero.com.ui.createjob;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import java.util.List;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;

public class CreateJobViewModel extends AndroidViewModel {

    JobRepository jobrepository;
    MutableLiveData<String> search;
    LiveData<List<Job>> jobsbysearch;


    public CreateJobViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
        if(search == null)
            search = new MutableLiveData();
        if(jobsbysearch == null)
            jobsbysearch = Transformations.switchMap(search, search -> jobrepository.contains(search));
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

}
