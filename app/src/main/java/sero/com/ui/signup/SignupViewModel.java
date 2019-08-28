package sero.com.ui.signup;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.UserRepository;
import sero.com.util.SharedPreferencesHelper;

public class SignupViewModel extends AndroidViewModel {

    UserRepository userRepository;

    public SignupViewModel(Application application) {
        super(application);
        if(userRepository == null)
            userRepository = RepositoryFactory.getUserRepository(application);
    }

    public void login(String login){
        SharedPreferencesHelper.login(login, getApplication());
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public boolean exist(String phone, String password){
        return userRepository.exist(phone, password);
    }

    public boolean exist(String phone){
        return userRepository.exist(phone);
    }
}
