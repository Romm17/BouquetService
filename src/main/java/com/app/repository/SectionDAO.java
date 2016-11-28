package com.app.repository;

import com.app.entity.Section;

import javax.ejb.Stateless;

/**
 * This class represents DAO for Section class
 * Created by romm on 13.11.16.
 */
@Stateless
public class SectionDAO extends AbstractDAO<Section, Integer> {

    /**
     *
     * @return class of Entity
     */
    @Override
    public Class<Section> getEntityClass() {
        return Section.class;
    }

}
