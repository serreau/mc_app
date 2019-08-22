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

    DashboardRepository dashboardrepository;
    String login;
    LiveData<User> user;
    LiveData<String> image;


    public DashboardViewModel(Application application) {
        super(application);

        login = SharedPreferencesHelper.getSp(application).getString("login", "error");

        if(dashboardrepository == null)
            dashboardrepository = RepositoryFactory.getDashboardRepository(application);

        if(user == null)
            user = dashboardrepository.get(login);
        if(image == null)
            image = Transformations.map(user, user -> user.getImage());
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void updateUser(User user) {
        dashboardrepository.update(user);
    }

    public void setImage(Uri image) {
        dashboardrepository.updateImage(login, image.toString());
    }
}
