package com.nazarov.javadeveloper.chapter23.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T findById(ID id);
    T findByName(String name);
    List<T> findAll();
    T save(T o);
    T update(T o);
    void remove(T o);

}
