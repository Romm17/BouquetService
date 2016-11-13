package com.app.dao;

import com.app.entity.Section;

/**
 * Created by romm on 13.11.16.
 */
public class SectionDAO extends AbstractDAO<Section, Integer> {

    @Override
    public Class<Section> getEntityClass() {
        return Section.class;
    }

}
