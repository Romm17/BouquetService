package com.app.repository;

import com.app.entity.User;

import javax.ejb.Stateless;

/**
 * Created by romm on 21.11.16.
 */
@Stateless
public class UserDAO extends AbstractDAO<User, Integer> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

}
