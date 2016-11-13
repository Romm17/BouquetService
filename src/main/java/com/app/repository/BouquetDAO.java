package com.app.repository;

import com.app.entity.Bouquet;

import javax.ejb.Stateless;

/**
 * Created by romm on 13.11.16.
 */
@Stateless
public class BouquetDAO extends AbstractDAO<Bouquet, Integer> {

    @Override
    public Class<Bouquet> getEntityClass() {
        return Bouquet.class;
    }

}
