package sero.com.ui.todo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;
import sero.com.util.State;

public class ToDoViewModel extends AndroidViewModel {

    JobRepository jobrepository;
    UserRepository userrepository;

    MutableLiveData<String> search;
    LiveData<List<Job>> jobsbysearch;
    LiveData<List<User>> userbyjob;


    public ToDoViewModel(Application application) {

        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
        if(search == null)
            search = new MutableLiveData();
        if(jobsbysearch == null)
            jobsbysearch = Transformations.switchMap(search, search -> {
                if("public".equals(SharedPreferencesHelper.getKanban(application)))
                    return jobrepository.getByStateSearch(State.TODO.toString(), search);
                else
                    return jobrepository.getByOwnerStateSearch(SharedPreferencesHelper.getKanban(application), State.TODO.toString(), search);
            });
        if(userbyjob == null)
            userbyjob = Transformations.switchMap(jobsbysearch, jobs -> {
                List<String> al = new ArrayList();
                jobs.forEach(job -> {
                    al.add(job.getOwner());
                });
                String[] array = al.toArray(new String[al.size()]);
                return userrepository.get(array);
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

    public LiveData<List<Job>> searchResult(){
        return jobsbysearch;
    }

    public void setSearch(String text){
        search.setValue(text);
    }

    public LiveData<List<User>> getUsers(){
        return userbyjob;
    }
}
