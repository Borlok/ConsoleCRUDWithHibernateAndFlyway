package com.borlok.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);
    T getById(ID id);
    T update(T t, ID id);
    List<T> getAll();
    void delete(ID id);
}
