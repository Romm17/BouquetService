package com.app.dao;

import com.app.entity.Customer;

/**
 * Created by romm on 13.11.16.
 */
public class CustomerDAO extends AbstractDAO<Customer, Integer> {

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

}
