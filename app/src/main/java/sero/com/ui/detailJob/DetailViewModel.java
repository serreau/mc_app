package sero.com.ui.detailJob;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;

public class DetailViewModel extends AndroidViewModel {
    JobRepository jobRepository;
    UserRepository userRepository;

    MutableLiveData<Long> jobId;
    LiveData<Job> job;

    public DetailViewModel(Application application) {
        super(application);
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
        if(jobId == null)
            jobId = new MutableLiveData<>();
        if(job == null)
            job = Transformations.switchMap(jobId, id -> jobRepository.get(id));
    }

    public void setJobId(Long jobId) {
        this.jobId.setValue(jobId);
    }

    public LiveData<Job> getJob() {
        return job;
    }

    public LiveData<User> getUser(String login) {
        return userRepository.get(login);
    }
}
