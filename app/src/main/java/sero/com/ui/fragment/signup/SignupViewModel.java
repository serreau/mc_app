package sero.com.ui.fragment.signup;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import sero.com.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;
import sero.com.util.LoginManager;

public class SignupViewModel extends AndroidViewModel {

    UserRepository userrepository;

    public SignupViewModel(Application application) {
        super(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        LoginManager.login(login, getApplication());
    }

    public void insert(User user){
        userrepository.insert(user);
    }

    public boolean exist(String phone, String password){
        return userrepository.exist(phone, password);
    }

    public boolean exist(String phone){
        return userrepository.exist(phone);
    }
}
