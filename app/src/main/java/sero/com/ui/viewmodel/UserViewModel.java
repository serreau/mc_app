package sero.com.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import sero.com.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    UserRepository userrepository;
    LiveData<User> user;
    MutableLiveData<String> phone;

    public UserViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
        if(phone == null)
            phone = new MutableLiveData<>();
        if(user == null)
            user = Transformations.switchMap(phone, phone -> {
                return userrepository.get(Long.parseLong(phone));
            });
    }

    public void login(String phone){
        this.phone.setValue(phone);
    }

    public void insert(User user){
        userrepository.insert(user);
    }

    public LiveData<User> getUser(){ return user; }

    public boolean exist(long phone, String password){
        return userrepository.exist(phone, password);
    }

    public boolean exist(long phone){
        return userrepository.exist(phone);
    }
}
