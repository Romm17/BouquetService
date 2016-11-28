package com.app.service;

import com.app.entity.BouquetOrder;
import com.app.repository.BouquetOrderDAO;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.*;
import java.util.List;

/**
 * This class represents methods for displaying and removing orders by id
 * Created by romm on 21.11.16.
 */
@ManagedBean
@ViewScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderDisplayService {

    /**
     * Application logger
     */
    private static final Logger logger = Logger.getLogger(OrderDisplayService.class);

    /**
     * DAO to access entities
     */
    @EJB
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        logger.info("OrderDisplayService created.");
    }

    /**
     * Dislays all orders
     * @return all orders
     */
    public List<BouquetOrder> getAllOrders() {
        logger.info("Getting all orders...");
        return orderRepository.getOrderList();
    }

    /**
     * Removed order by id
     * @param bouquetOrderId
     */
    public void removeOrderById(Integer bouquetOrderId) {
        orderRepository.removeOrderById(bouquetOrderId);
    }

}