package com.app.service;

import com.app.entity.Bouquet;
import com.app.repository.BouquetDAO;

import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romm on 13.11.16.
 */
@ManagedBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BouquetService {

    @EJB
    private BouquetDAO bouquetDAO;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bouquet> getAllBouquets() {
        return bouquetDAO.getAll();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bouquet> getAllBouquetsCheaperThan(Double upperBound) {
        List<Bouquet> bouquets = bouquetDAO.getAll();
        return bouquets.stream().filter(x -> x.getPrice() <= upperBound).collect(Collectors.toList());
    }

}
