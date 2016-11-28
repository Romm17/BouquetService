package com.app.service;

import com.app.entity.BouquetOrder;
import com.app.repository.BouquetOrderDAO;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class persists actual list of orders
 * Created by romm on 22.11.16.
 */
@Stateful
public class OrderRepository {

    /**
     * Application logger
     */
    private static final Logger logger = Logger.getLogger(OrderRepository.class);

    /**
     * DAO to access entities
     */
    @EJB
    private BouquetOrderDAO orderDAO;

    /**
     * Order map for fast update by id
     */
    private Map<Integer, BouquetOrder> orderMap;

    /**
     * Order list sorted by id
     */
    private AtomicReference<List<BouquetOrder> > orderList;

    /**
     * Loading orders from database
     */
    @PostConstruct
    public void init() {
        logger.info("OrderRepository created.");
        orderMap = new ConcurrentHashMap<>();
        orderList = new AtomicReference<>(orderDAO.getAll());
        list2map();
    }

    public List<BouquetOrder> getOrderList() {
        logger.info("Getting all orders...");
        return orderList.get();
    }

    public void updateOrderById(Integer bouquetOrderId) {
        BouquetOrder order = orderDAO.get(bouquetOrderId);
        orderMap.put(order.getId(), order);
        map2list();
    }

    public void removeOrderById(Integer bouquetOrderId) {
        orderMap.remove(bouquetOrderId);
        map2list();
        orderDAO.delete(orderDAO.get(bouquetOrderId));
    }

    /**
     * Converts order list to order map
     */
    private void list2map() {
        orderMap.clear();
        for (BouquetOrder order : orderList.get()) {
            orderMap.put(order.getId(), order);
        }
    }

    /**
     * Converts order map to order list
     */
    private void map2list() {
        List<BouquetOrder> collection = new ArrayList<>(orderMap.values());
        Collections.sort(collection);
        orderList.set(collection);
    }
}
