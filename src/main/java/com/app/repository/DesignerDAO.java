package com.app.repository;

import com.app.entity.Designer;

import javax.ejb.Stateless;

/**
 * This class represents DAO for Designer class
 * Created by romm on 13.11.16.
 */
@Stateless
public class DesignerDAO extends AbstractDAO<Designer, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<Designer> getEntityClass() {
        return Designer.class;
    }

}
