package sero.com.ui.createjob;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class CreateJobViewModel extends AndroidViewModel {

    JobRepository jobrepository;
    UserRepository userrepository;

    LiveData<String> firstname;

    public CreateJobViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
        if(firstname == null)
            firstname = userrepository.getFirstname(SharedPreferencesHelper.getSp(application).getString("login", "error"));
    }

    public void insert(Job job){
        jobrepository.insert(job);
    }

    public LiveData<String> getFirstname(){
        return firstname;
    }

}
