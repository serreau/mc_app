package sero.com.ui.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class LoginViewModel extends AndroidViewModel {

    UserRepository userRepository;

    public LoginViewModel(Application application) {
        super(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        SharedPreferencesHelper.login(login, getApplication());
    }

    public boolean exist(String login, String password){
        return userRepository.exist(login, password);
    }

}
