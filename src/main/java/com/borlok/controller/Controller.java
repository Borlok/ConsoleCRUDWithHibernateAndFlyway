package com.borlok.controller;

import java.util.List;

public interface Controller<T> {
    T create(T t);
    List<T> getAll();
    T update(T t, Integer id);
    void delete(Integer id);

}
