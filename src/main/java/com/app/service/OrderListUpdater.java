package com.app.service;

import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * MDB for updating order list in orderRepository
 * Created by romm on 22.11.16.
 */
@MessageDriven(
        mappedName="orderListUpdater",
        activationConfig =
        {
                @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:jboss/exported/jms/topic/test"),
                @ActivationConfigProperty(propertyName="connectionFactoryName", propertyValue="java:/JmsXA"),
                @ActivationConfigProperty(propertyName="MaxPoolSize", propertyValue="10"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        })
public class OrderListUpdater implements MessageListener {

    /**
     * Application logger
     */
    private static final Logger logger = Logger.getLogger(OrderListUpdater.class);

    /**
     * DAO to access entities
     */
    @EJB
    private OrderRepository orderRepository;

    /**
     * Updates order in orderRepository list by id given in message text
     * @param message with orderId
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String messageText = ((TextMessage) message).getText();
                Integer bouquetOrderId = Integer.parseInt(messageText.trim());
                logger.info("Updating bouquetOrder with id = " + bouquetOrderId);
                orderRepository.updateOrderById(bouquetOrderId);
            } catch (JMSException e) {
                logger.error("Error in onMessage : " + e);
            }
        } else {
            logger.warn("Message is not instance of text message!");
        }
    }
}
