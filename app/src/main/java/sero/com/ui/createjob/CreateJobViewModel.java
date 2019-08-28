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

    JobRepository jobRepository;
    UserRepository userRepository;

    LiveData<String> firstname;

    public CreateJobViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
        if(firstname == null)
            firstname = userRepository.getFirstname(SharedPreferencesHelper.getlogin(application));
    }

    public void insert(Job job){
        jobRepository.insert(job);
    }

    public LiveData<String> getFirstname(){
        return firstname;
    }

}
