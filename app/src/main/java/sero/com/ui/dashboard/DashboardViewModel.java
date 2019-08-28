package sero.com.ui.dashboard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.net.Uri;

import sero.com.data.Factories.RepositoryFactory;
import sero.com.data.entities.User;
import sero.com.data.repositories.DashboardRepository;
import sero.com.util.SharedPreferencesHelper;

public class DashboardViewModel extends AndroidViewModel {

    DashboardRepository dashboardRepository;
    String login;
    LiveData<User> user;
    LiveData<String> image;


    public DashboardViewModel(Application application) {
        super(application);

        login = SharedPreferencesHelper.getlogin(application);

        if(dashboardRepository == null)
            dashboardRepository = RepositoryFactory.getDashboardRepository(application);

        if(user == null)
            user = dashboardRepository.get(login);
        if(image == null)
            image = Transformations.map(user, user -> user.getImage());
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void updateUser(User user) {
        dashboardRepository.update(user);
    }

    public void setImage(Uri image) {
        dashboardRepository.updateImage(login, image.toString());
    }
}
