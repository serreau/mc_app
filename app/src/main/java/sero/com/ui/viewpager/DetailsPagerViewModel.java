package sero.com.ui.viewpager;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.Offer;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.OfferRepository;
import sero.com.util.SharedPreferencesHelper;

public class DetailsPagerViewModel extends AndroidViewModel {
    private JobRepository jobRepository;

    private MutableLiveData<Long> jobId ;
    private LiveData<Job> job;
    private LiveData<Boolean> isOwner;

    public DetailsPagerViewModel(Application application) {
        super(application);
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(jobId == null)
            jobId = new MutableLiveData<>();
        if(job == null)
            job = Transformations.switchMap(jobId, jobId -> jobRepository.get(jobId));
        if(isOwner == null)
            isOwner = Transformations.switchMap(job, job -> jobRepository.isOwner(SharedPreferencesHelper.getlogin(application), job.getId()));
    }

    public void setJobId(Long jobId) {
        this.jobId.setValue(jobId);
    }

    public LiveData<Boolean> isOwner(){
        return isOwner;
    }
}
