package sero.com.ui.fragment.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import sero.com.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;
import sero.com.util.LoginManager;

public class LoginViewModel extends AndroidViewModel {

    UserRepository userrepository;

    public LoginViewModel(Application application) {
        super(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        User user = userrepository.get(login);
        LoginManager.login(user, getApplication());
    }

    public boolean exist(String login, String password){
        return userrepository.exist(login, password);
    }

}
