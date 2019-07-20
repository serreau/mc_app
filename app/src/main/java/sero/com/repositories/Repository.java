package sero.com.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface Repository<T> {
    void insert(T object);
    void delete(T object);
    void update(T object);
    LiveData<T> get(long id);
}
