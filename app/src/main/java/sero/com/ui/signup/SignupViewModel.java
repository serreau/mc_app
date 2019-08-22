package sero.com.ui.signup;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class SignupViewModel extends AndroidViewModel {

    UserRepository userrepository;

    public SignupViewModel(Application application) {
        super(application);
        if(userrepository == null)
            userrepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        SharedPreferencesHelper.login(login, getApplication());
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
