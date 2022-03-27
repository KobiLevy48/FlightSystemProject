package dao;

import java.util.List;

public interface DAO <T>{
    T get(int id);
    List<T> getAll();
    void add(T t);
    void remove(T t);
    void update(T t);
}
