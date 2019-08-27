package sero.com.ui.detailJob;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import javax.xml.transform.TransformerFactory;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;

public class DetailViewModel extends AndroidViewModel {
    JobRepository jobrepository;
    UserRepository userrepository;

    MutableLiveData<Long> jobid;
    LiveData<Job> job;

    public DetailViewModel(Application application) {
        super(application);
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
        if(jobid == null)
            jobid = new MutableLiveData<>();
        if(job == null)
            job = Transformations.switchMap(jobid, id -> jobrepository.get(id));
    }

    public void setJobId(Long jobid) {
        this.jobid.setValue(jobid);
    }

    public LiveData<Job> getJob() {
        return job;
    }

    public LiveData<User> getUser(String login) {
        return userrepository.get(login);
    }
}
