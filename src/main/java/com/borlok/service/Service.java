package com.borlok.service;

import java.util.List;

public interface Service<T> {
    T create(T t);
    List<T> getAll();
    T update(T t, Integer id);
    void delete(Integer id);
}
