package sero.com.data.Factories;

import android.content.Context;

import sero.com.data.repositories.DashboardRepository;
import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.UserRepository;
import sero.com.data.repositories.impl.DashboardRepository_Room;
import sero.com.data.repositories.impl.JobRepository_Room;
import sero.com.data.repositories.impl.UserRepository_Room;

public class RepositoryFactory {
    public static JobRepository getJobRepository(Context context){
        return new JobRepository_Room(context);

    }
    public static UserRepository getUserRepository(Context context){
        return new UserRepository_Room(context);
    }

    public static DashboardRepository getDashboardRepository(Context context){
        return new DashboardRepository_Room(context);
    }
}
