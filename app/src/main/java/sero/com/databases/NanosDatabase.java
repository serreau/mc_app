package sero.com.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sero.com.dao.NanosDao;
import sero.com.entities.Nanos;

@Database(entities = {Nanos.class}, version = 1)
public abstract class NanosDatabase extends RoomDatabase {

    public abstract NanosDao nanosDao();

    private static NanosDatabase INSTANCE;

    static NanosDatabase getInstance(final Context context) {
        synchronized (NanosDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NanosDatabase.class, "nanos_database")
                        .build();
            }
        }
        return INSTANCE;
    }
}
