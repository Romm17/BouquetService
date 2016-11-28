package com.app.repository;

import com.app.entity.BouquetOrder;

import javax.ejb.Stateless;

/**
 * This class represents DAO for BouquetOrder class
 * Created by romm on 13.11.16.
 */
@Stateless
public class BouquetOrderDAO extends AbstractDAO<BouquetOrder, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<BouquetOrder> getEntityClass() {
        return BouquetOrder.class;
    }

}
