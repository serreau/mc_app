package sero.com.ui.fragment.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

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
        LoginManager.login(login, getApplication());
    }

    public boolean exist(String login, String password){
        return userrepository.exist(login, password);
    }

}
