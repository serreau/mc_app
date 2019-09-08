package sero.com.ui.offers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.Job;
import sero.com.data.entities.Offer;
import sero.com.data.entities.User;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.OfferRepository;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class OffersViewModel extends AndroidViewModel {

    OfferRepository offerRepository;
    UserRepository userRepository;

    MutableLiveData<Long> jobId;
    LiveData<Job> job;
    LiveData<List<Offer>> offers;
    LiveData<List<User>> users;

    public OffersViewModel(Application application) {
        super(application);
        if(offerRepository == null)
            offerRepository = RepositoryFactory.getOfferRepository(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
        if(jobId == null)
            jobId = new MutableLiveData<>();
        if(offers == null)
            offers = Transformations.switchMap(jobId, jobId -> offerRepository.getByJob(jobId));
        if(users == null)
            users = Transformations.switchMap(offers, offers -> {
                List<String> al = new ArrayList();
                offers.forEach(offer -> {
                    al.add(offer.getSender());
                });
                String[] array = al.toArray(new String[al.size()]);
                return userRepository.get(array);
            });
    }

    public void setJobId(Long jobId){
        this.jobId.setValue(jobId);
    }

    public LiveData<List<Offer>> getOffers() {
        return offers;
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}
