package com.app.repository;

import com.app.entity.Designer;

import javax.ejb.Stateless;

/**
 * Created by romm on 13.11.16.
 */
@Stateless
public class DesignerDAO extends AbstractDAO<Designer, Integer> {

    @Override
    public Class<Designer> getEntityClass() {
        return Designer.class;
    }

}
