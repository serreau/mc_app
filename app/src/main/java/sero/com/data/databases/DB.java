package sero.com.data.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.security.CryptoPrimitive;

import sero.com.data.dao.JobDao;
import sero.com.data.dao.UserDao;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;

@Database(entities = {Job.class, User.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public abstract JobDao jobDao();
    public abstract UserDao userDao();

    public final static synchronized DB getInstance(final Context context) {
        if(INSTANCE == null)
            INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), DB.class, "subapp-db")
                .allowMainThreadQueries()
                .build();
        //populate();
        return INSTANCE;
    }

    private static void populate() {
        User u = new User();
        u.setPhone(1);
        u.setPassword("b");

        if(!INSTANCE.userDao().exist(1,"b"))
            INSTANCE.userDao().insert(u);
    }

}
