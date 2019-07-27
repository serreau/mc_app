package sero.com.Factories;

import android.content.Context;

import sero.com.data.repositories.JobRepository;
import sero.com.data.repositories.impl.JobRepository_Room;

public class RepositoryFactory {
    public enum Entities{
        JOB
    }

    public static JobRepository getJobRepository(Entities entities, Context context){
        switch (entities){
            case JOB:
                return new JobRepository_Room(context);
            default:
                throw new IllegalArgumentException("Table inconnue");
        }
    }

}
