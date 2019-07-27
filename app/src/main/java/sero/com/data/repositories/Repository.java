package sero.com.data.repositories;

import android.arch.lifecycle.LiveData;

public interface Repository<T> {
    void insert(T object);
    void delete(T object);
    void update(T object);
    LiveData<T> get(long id);
}
