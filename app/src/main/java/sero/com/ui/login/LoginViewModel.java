package sero.com.ui.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class LoginViewModel extends AndroidViewModel {

    UserRepository userrepository;

    public LoginViewModel(Application application) {
        super(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        SharedPreferencesHelper.login(login, getApplication());
    }

    public boolean exist(String login, String password){
        return userrepository.exist(login, password);
    }

}
