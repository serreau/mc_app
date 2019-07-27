package sero.com.data.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import sero.com.data.dao.JobDao;
import sero.com.data.entities.Job;

@Database(entities = {Job.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public abstract JobDao jobDao();

    public final static synchronized DB getInstance(final Context context) {
        if(INSTANCE == null)
            INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), DB.class, "subapp-db")
                .allowMainThreadQueries()
                .build();
        return INSTANCE;
    }
}
