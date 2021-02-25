package com.nazarov.javadeveloper.chapter23.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id);
    T getByName(String name);
    List<T> getAll();
    T save(T o);
    T update(T o);
    void remove(T o);

}
