package com.app.dao;

import com.app.entity.BouquetOrder;

/**
 * Created by romm on 13.11.16.
 */
public class BouquetOrderDAO extends AbstractDAO<BouquetOrder, Integer> {

    @Override
    public Class<BouquetOrder> getEntityClass() {
        return BouquetOrder.class;
    }
    
}
