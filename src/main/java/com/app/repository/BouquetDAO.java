package com.app.repository;

import com.app.entity.Bouquet;

import javax.ejb.Stateless;

/**
 * This class represents DAO for Bouquet class
 * Created by romm on 13.11.16.
 */
@Stateless
public class BouquetDAO extends AbstractDAO<Bouquet, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<Bouquet> getEntityClass() {
        return Bouquet.class;
    }

}
