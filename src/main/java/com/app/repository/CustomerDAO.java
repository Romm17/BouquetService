package com.app.repository;

import com.app.entity.Customer;

import javax.ejb.Stateless;

/**
 * This class represents DAO for Customer class
 * Created by romm on 13.11.16.
 */
@Stateless
public class CustomerDAO extends AbstractDAO<Customer, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

}
