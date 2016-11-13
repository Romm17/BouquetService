package com.app.dao;

import com.app.entity.Designer;

/**
 * Created by romm on 13.11.16.
 */
public class DesignerDAO extends AbstractDAO<Designer, Integer> {

    @Override
    public Class<Designer> getEntityClass() {
        return Designer.class;
    }

}
