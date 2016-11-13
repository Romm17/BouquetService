package com.app.dao;

import com.app.entity.Bouquet;

/**
 * Created by romm on 13.11.16.
 */
public class BouquetDAO extends AbstractDAO<Bouquet, Integer> {

    @Override
    public Class<Bouquet> getEntityClass() {
        return Bouquet.class;
    }

}
