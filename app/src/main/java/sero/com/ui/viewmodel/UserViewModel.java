package sero.com.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import sero.com.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    UserRepository userrepository;


    public UserViewModel(Application application) {
        super(application);
        //executor = new Executors.newSingleThreadExecutor();
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
    }

    public void insert(User user){
        userrepository.insert(user);
    }

    public LiveData<User> get(long phone){
        return userrepository.get(phone);
    }

    public boolean exist(long phone, String password){
        return userrepository.exist(phone, password);
    }

    public boolean exist(long phone){
        return userrepository.exist(phone);
    }
}
