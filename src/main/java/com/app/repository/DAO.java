package com.app.repository;

import java.io.Serializable;
import java.util.List;

/**
 * This interface declares all the methods needed to manage entities
 * Created by romm on 11.10.16.
 */
public interface DAO<T, PK extends Serializable> {

    /**
     *
     * @return class of entity to perform queries
     */
    Class<T> getEntityClass();

    /**
     *
     * @return all entities
     */
    List<T> getAll();

    /**
     * Persists given entity
     * @param entity
     */
    void create(T entity);

    /**
     * Get entity by key
     * @param key - entity's id
     * @return found entity
     */
    T get(PK key);

    /**
     * Updates given entity
     * @param entity
     */
    void update(T entity);

    /**
     * Deletes given entity
     * @param entity
     */
    void delete(T entity);

}
