package sero.com.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sero.com.dao.NanosDao;
import sero.com.entities.Nanos;

@Database(entities = {Nanos.class}, version = 1)
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public abstract NanosDao nanosDao();

    public final static synchronized DB getInstance(final Context context) {
        if(INSTANCE == null)
            INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), DB.class, "subapp-db")
                .allowMainThreadQueries()
                .build();
        return INSTANCE;
    }
}
