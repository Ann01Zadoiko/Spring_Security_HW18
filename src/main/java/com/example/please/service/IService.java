package com.example.please.service;


import java.util.List;

public interface IService<T> {
    List<T> listAll();

    T add(T entity);

    void deleteById(Long id);

    void update(T entity);

    T getById(Long id);
}
