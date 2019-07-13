package sero.com.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sero.com.dao.NanoDao;
import sero.com.entities.Nano;

@Database(entities = {Nano.class}, version = 1)
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public abstract NanoDao nanosDao();

    public final static synchronized DB getInstance(final Context context) {
        if(INSTANCE == null)
            INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), DB.class, "subapp-db")
                .allowMainThreadQueries()
                .build();
        INSTANCE.clearAllTables();
        return INSTANCE;
    }
}
