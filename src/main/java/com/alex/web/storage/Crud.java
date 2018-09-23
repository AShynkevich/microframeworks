package com.alex.web.storage;

import java.util.List;

public interface Crud<K, E> {

    K create(E entity);
    E read(K identity);
    void update(E entity);
    void delete(K identity);
    List<E> findAll();
}
