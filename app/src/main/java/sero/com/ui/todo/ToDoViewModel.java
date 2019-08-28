package sero.com.ui.todo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;
import sero.com.util.State;

public class ToDoViewModel extends AndroidViewModel {

    JobRepository jobRepository;
    UserRepository userRepository;

    MutableLiveData<String> search;
    LiveData<List<Job>> jobsBySearch;
    LiveData<List<User>> userByJob;

    public ToDoViewModel(Application application) {

        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
        if(search == null)
            search = new MutableLiveData();
        if(jobsBySearch == null)
            jobsBySearch = Transformations.switchMap(search, search -> {
                if("public".equals(SharedPreferencesHelper.getKanban(application)))
                    return jobRepository.getByStateSearch(State.TODO.toString(), search);
                else
                    return jobRepository.getByOwnerStateSearch(SharedPreferencesHelper.getKanban(application), State.TODO.toString(), search);
            });
        if(userByJob == null)
            userByJob = Transformations.switchMap(jobsBySearch, jobs -> {
                List<String> al = new ArrayList();
                jobs.forEach(job -> {
                    al.add(job.getOwner());
                });
                String[] array = al.toArray(new String[al.size()]);
                return userRepository.get(array);
            });

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

    public LiveData<List<Job>> searchResult(){
        return jobsBySearch;
    }

    public void setSearch(String text){
        search.setValue(text);
    }

    public LiveData<List<User>> getUsers(){
        return userByJob;
    }
}
