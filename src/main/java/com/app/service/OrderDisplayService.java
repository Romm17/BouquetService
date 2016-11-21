package com.app.service;

import com.app.entity.BouquetOrder;
import com.app.repository.BouquetOrderDAO;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.*;
import javax.jms.*;
import java.util.List;

/**
 * Created by romm on 21.11.16.
 */
@ManagedBean
@NoneScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderDisplayService {

    private static final Logger logger = Logger.getLogger(OrderDisplayService.class);

    @EJB
    private BouquetOrderDAO orderDAO;

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:jboss/exported/jms/topic/test")
    private Topic destination;

    private List<BouquetOrder> allOrders;

    @PostConstruct
    public void init() {
        logger.info("OrderDisplayService created.");
        allOrders = orderDAO.getAll();
    }

    public List<BouquetOrder> getAllOrders() {
        logger.info("Getting all orders...");
        return allOrders;
    }

    public void removeOrderById(Integer bouquetOrderId) {
        orderDAO.delete(orderDAO.get(bouquetOrderId));
    }

    public Integer getMessagesCounter() {
        logger.info("Trying to receive messages : ");
        Integer counter = 0;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            long id = Math.round(Math.random() * 1e8);
            logger.info("Subscriber id " + id);
            MessageConsumer consumer = session.createSharedDurableConsumer(destination, "" + id);
            connection.start();
            Message msg = null;
//            do {
            msg = consumer.receive();
            if (msg != null) {
                logger.info("Received message : " + msg.toString());
                allOrders = orderDAO.getAll();
                counter++;
            }
//            } while (msg != null);
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        return counter;
    }

}
