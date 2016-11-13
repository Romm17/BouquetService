package com.app.dao;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by romm on 11.10.16.
 */
public abstract class AbstractDAO<T, PK extends Serializable> implements DAO<T, PK> {

    @PersistenceContext(unitName = "app")
    private EntityManager entityManager;

    private static final Logger logger = Logger.getLogger(AbstractDAO.class);

    @Override
    public List<T> getAll() {
        List<T> result = null;
        try {
            result = entityManager.createQuery(
                    "SELECT t FROM " +
                            getEntityClass().getSimpleName()
                            + " t",
                    getEntityClass()
            ).getResultList();
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T get(PK key) {
        return entityManager.find(getEntityClass(), key);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

}
