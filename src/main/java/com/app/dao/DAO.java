package com.app.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by romm on 11.10.16.
 */
public interface DAO<T, PK extends Serializable> {

    Class<T> getEntityClass();

    List<T> getAll();

    void create(T entity);

    T get(PK key);

    void update(T entity);

    void delete(T entity);

}
