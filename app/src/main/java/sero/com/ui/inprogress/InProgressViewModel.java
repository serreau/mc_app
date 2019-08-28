package sero.com.ui.inprogress;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import java.util.List;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;
import sero.com.util.State;

public class InProgressViewModel extends AndroidViewModel {

    JobRepository jobRepository;
    MutableLiveData<String> search;
    LiveData<List<Job>> jobsBySearch;


    public InProgressViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(search == null)
            search = new MutableLiveData();
        if(jobsBySearch == null)
            jobsBySearch = Transformations.switchMap(search, search -> jobRepository.getByStateSearch(State.INPROGRESS.toString(), search));
    }

    public void insert(Job job){
        jobRepository.insert(job);
    }

    public void delete(Job job){
        jobRepository.delete(job);
    }

    public void update(Job job){
        jobRepository.update(job);
    }

    public LiveData<Job> get(long id){
        return jobRepository.get(id);
    }

    public LiveData<List<Job>> get(){
        return jobRepository.get();
    }

    public LiveData<List<Job>> searchResult(){ return jobsBySearch; }

    public void setSearch(String text){ search.setValue(text); }

}
