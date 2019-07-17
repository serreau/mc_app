package sero.com.repositories;

import java.util.List;

public interface Repository<T> {
    void insert(T object);
    void delete(T object);
    void update(T object);
    T get(long id);
    List<T> getAll();
}
