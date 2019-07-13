package repositories.impl;

import android.content.Context;

import java.util.List;

import repositories.Repository;
import sero.com.dao.NanoDao;
import sero.com.databases.DB;
import sero.com.entities.Nano;

public class NanoRepository_Room implements Repository<Nano> {
    DB db;
    NanoDao nanoDao;

    public NanoRepository_Room(Context context){
        db = DB.getInstance(context);
        nanoDao = db.nanosDao();
    }

    @Override
    public void update(Nano object) {
        nanoDao.update(object);
    }

    @Override
    public void insert(Nano object) {
        nanoDao.insert(object);
    }

    @Override
    public void delete(Nano object) {
        nanoDao.delete(object);
    }

    @Override
    public Nano get(long id) {
        return nanoDao.get(id);
    }

    @Override
    public List<Nano> getAll() {
        return nanoDao.getAll();
    }
}
