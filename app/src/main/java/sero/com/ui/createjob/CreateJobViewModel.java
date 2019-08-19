package sero.com.ui.createjob;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.repositories.JobRepository;

public class CreateJobViewModel extends AndroidViewModel {

    JobRepository jobrepository;


    public CreateJobViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
    }

    public void insert(Job job){
        jobrepository.insert(job);
    }


}
