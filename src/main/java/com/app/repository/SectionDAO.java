package com.app.repository;

import com.app.entity.Section;

import javax.ejb.Stateless;

/**
 * Created by romm on 13.11.16.
 */
@Stateless
public class SectionDAO extends AbstractDAO<Section, Integer> {

    @Override
    public Class<Section> getEntityClass() {
        return Section.class;
    }

}
