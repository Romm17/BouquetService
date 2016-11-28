package com.app.service;

import com.app.entity.Section;
import com.app.repository.SectionDAO;

import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by romm on 15.11.16.
 */
@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SectionService {

    /**
     * DAO to access entities
     */
    @EJB
    private SectionDAO sectionDAO;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Section> getAll() {
        return sectionDAO.getAll();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Section getSectionById(Integer id) {
        return sectionDAO.get(id);
    }

}
