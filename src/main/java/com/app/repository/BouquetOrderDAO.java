package com.app.repository;

import com.app.entity.BouquetOrder;

import javax.ejb.Stateless;

/**
 * Created by romm on 13.11.16.
 */
@Stateless
public class BouquetOrderDAO extends AbstractDAO<BouquetOrder, Integer> {

    @Override
    public Class<BouquetOrder> getEntityClass() {
        return BouquetOrder.class;
    }

}
