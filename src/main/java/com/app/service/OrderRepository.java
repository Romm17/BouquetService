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
 * Created by romm on 22.11.16.
 */
@Stateful

public class OrderRepository {

    private static final Logger logger = Logger.getLogger(OrderRepository.class);

    @EJB
    private BouquetOrderDAO orderDAO;

    private Map<Integer, BouquetOrder> orderMap;

    private AtomicReference<List<BouquetOrder> > orderList;

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

    private void list2map() {
        orderMap.clear();
        for (BouquetOrder order : orderList.get()) {
            orderMap.put(order.getId(), order);
        }
    }

    private void map2list() {
        List<BouquetOrder> collection = new ArrayList<>(orderMap.values());
        Collections.sort(collection);
        orderList.set(collection);
    }
}
