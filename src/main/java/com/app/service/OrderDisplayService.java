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
 * Created by romm on 21.11.16.
 */
@ManagedBean
@ViewScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderDisplayService {

    private static final Logger logger = Logger.getLogger(OrderDisplayService.class);

    @EJB
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        logger.info("OrderDisplayService created.");
    }

    public List<BouquetOrder> getAllOrders() {
        logger.info("Getting all orders...");
        return orderRepository.getOrderList();
    }

    public void removeOrderById(Integer bouquetOrderId) {
        orderRepository.removeOrderById(bouquetOrderId);
    }

}