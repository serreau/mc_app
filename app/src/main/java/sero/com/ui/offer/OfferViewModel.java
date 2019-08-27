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
    OfferRepository offerrepository;
    JobRepository jobrepository;

    MutableLiveData<Long> jobid;
    LiveData<Job> job;
    LiveData<Offer> offer;

    Job newjob;

    public OfferViewModel(Application application) {
        super(application);
        this.application = application;
        if(offerrepository == null)
            offerrepository = RepositoryFactory.getOfferRepository(application);
        if(jobrepository == null)
            jobrepository = RepositoryFactory.getJobRepository(application);
        if(jobid == null)
            jobid = new MutableLiveData<>();
        if(job == null)
            job = Transformations.switchMap(jobid, input -> jobrepository.get(input));
        if(offer == null)
            offer = Transformations.switchMap(jobid, input -> {
                return offerrepository.getBySenderAndJob(SharedPreferencesHelper.getlogin(application), input);
            });
    }

    public void createOffer(Offer offer) {

        offerrepository.insert(offer);
    }

    public LiveData<Job> getJob() {
        return job;
    }

    public void setJobId(long id) {
            this.jobid.setValue(id);
    }

    public LiveData<Offer> getOffer() {
        return offer;
    }
}
