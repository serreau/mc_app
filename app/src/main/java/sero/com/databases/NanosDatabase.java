package sero.com.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import sero.com.dao.NanosDao;
import sero.com.entities.Nanos;

@Database(entities = {Nanos.class}, version = 1)
public abstract class NanosDatabase extends RoomDatabase {

    public abstract NanosDao nanosDao();
}
