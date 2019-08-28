package sero.com.ui.offer;

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

public class OfferViewModel extends AndroidViewModel {

    Application application;
    OfferRepository offerRepository;
    JobRepository jobRepository;

    MutableLiveData<Long> jobId;
    LiveData<Job> job;
    LiveData<Offer> offer;

    public OfferViewModel(Application application) {
        super(application);
        this.application = application;
        if(offerRepository == null)
            offerRepository = RepositoryFactory.getOfferRepository(application);
        if(jobRepository == null)
            jobRepository = RepositoryFactory.getJobRepository(application);
        if(jobId == null)
            jobId = new MutableLiveData<>();
        if(job == null)
            job = Transformations.switchMap(jobId, input -> jobRepository.get(input));
        if(offer == null)
            offer = Transformations.switchMap(jobId, input -> {
                return offerRepository.getBySenderAndJob(SharedPreferencesHelper.getlogin(application), input);
            });
    }

    public void createOffer(Offer offer) {

        offerRepository.insert(offer);
    }

    public LiveData<Job> getJob() {
        return job;
    }

    public void setJobId(long id) {
            this.jobId.setValue(id);
    }

    public LiveData<Offer> getOffer() {
        return offer;
    }
}
