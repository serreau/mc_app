package sero.com.ui.fragment.dashboard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import sero.com.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.DashboardRepository;
import sero.com.util.LoginManager;

public class DashboardViewModel extends AndroidViewModel {

    DashboardRepository dashboardrepository;
    MutableLiveData<User> user;
    LiveData<String> phone;
    LiveData<String> mail;
    LiveData<String> firstname;
    LiveData<String> lastname;
    LiveData<String> password;


    public DashboardViewModel(Application application) {
        super(application);

        if(dashboardrepository == null)
            dashboardrepository = RepositoryFactory.getDashboardRepository(application);

        if(user == null)
            user = new MutableLiveData<>();
        user.setValue(LoginManager.getUser(application));
        if(phone == null)
            phone = Transformations.map(user, user -> user.getPhone());
        if(mail == null)
            mail = Transformations.map(user, user -> user.getMail());
        if(firstname == null)
            firstname = Transformations.map(user, user -> user.getFirstname());
        if(lastname == null)
            lastname = Transformations.map(user, user -> user.getLastname());
        if(password == null)
            password = Transformations.map(user, user -> user.getPassword());
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void updateUser(User user) {
        this.user.setValue(user);
        dashboardrepository.update(user);
    }
}