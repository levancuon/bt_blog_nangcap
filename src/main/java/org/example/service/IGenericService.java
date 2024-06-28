package org.example.service;

import org.example.model.Blog;

import java.util.Optional;

public interface IGenericService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
