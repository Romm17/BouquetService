package com.app.repository;

import com.app.entity.User;

import javax.ejb.Stateless;

/**
 * This class represents DAO for User class
 * Created by romm on 21.11.16.
 */
@Stateless
public class UserDAO extends AbstractDAO<User, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

}
