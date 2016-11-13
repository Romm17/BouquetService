package com.app.repository;

import com.app.entity.Customer;

import javax.ejb.Stateless;

/**
 * Created by romm on 13.11.16.
 */
@Stateless
public class CustomerDAO extends AbstractDAO<Customer, Integer> {

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

}
