package com.app.service;

import com.app.entity.Bouquet;
import com.app.entity.BouquetOrder;
import com.app.entity.BouquetOrderStatus;
import com.app.repository.BouquetDAO;
import com.app.repository.BouquetOrderDAO;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.jms.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by romm on 15.11.16.
 */
@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class);

    @EJB
    private BouquetOrderDAO orderDAO;

    @EJB
    private BouquetDAO bouquetDAO;

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:jboss/exported/jms/topic/test")
    private Topic destination;

    private Integer orderId;

    private Integer bouquetId;

    public void addBouquetToOrder(Integer bouquetId) {
        readOrderId();
        Bouquet bouquet = bouquetDAO.get(bouquetId);
        if (orderId == null) {
            BouquetOrder order = new BouquetOrder();
            order.setStatus(BouquetOrderStatus.IN_PROGRESS);
            List<Bouquet> bouquetList = new LinkedList<>();
            bouquetList.add(bouquet);
            order.setBouquets(bouquetList);
            orderDAO.create(order);
            orderId = order.getId();
            writeOrderId();
        }
        else {
            BouquetOrder order = orderDAO.get(orderId);
            order.addBouquet(bouquet);
            orderDAO.update(order);
        }
        sendMessage(orderId);
    }

    public void removeBouquetFromOrder(Integer bouquetId) {
        readOrderId();
        BouquetOrder order = orderDAO.get(orderId);
        Bouquet bouquet = bouquetDAO.get(bouquetId);
        order.removeBouquet(bouquet);
        orderDAO.update(order);
    }

    public void readOrderId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Object orderIdParam = session.getAttribute("orderId");
        orderId = null;
        if(orderIdParam != null) {
            orderId = Integer.parseInt(orderIdParam.toString().trim());
        }
    }

    public void writeOrderId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("orderId", "" + orderId);
    }

    public void clearAll() {
        if (orderId != null) {
            BouquetOrder order = orderDAO.get(orderId);
            order.getBouquets().clear();
            orderDAO.update(order);
        }
    }

    public void buyAll() {
        if (orderId != null) {
            BouquetOrder order = orderDAO.get(orderId);
            order.setStatus(BouquetOrderStatus.BOUGHT);
            orderDAO.update(order);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.removeAttribute("orderId");
            orderId = null;
        }
    }

    public List<Bouquet> getAllBouquetsInOrder() {
        readOrderId();
        if (orderId != null) {
            return orderDAO.get(orderId).getBouquets();
        }
        return new LinkedList<>();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(Integer bouquetId) {
        this.bouquetId = bouquetId;
    }

    public void sendMessage(Integer bouquetOrderId) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage();
            message.setText("" + bouquetOrderId);
            producer.send(message);
            logger.warn("Sending message: " + message.getText());
            session.close();
            connection.close();
        } catch (JMSException ex) {
            logger.error("Sending message failed : " + ex);
        }
    }

}
