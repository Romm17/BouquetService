package com.app.service;

import com.app.entity.Bouquet;
import com.app.entity.BouquetOrder;
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
        try {
            //создаем подключение
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage();
            //добавим в JMS сообщение собственное свойство в поле сообщения со свойствами
            message.setStringProperty("clientType", "web client");
            //добавляем payload в сообщение
            message.setText("Bouquet Added");
            //отправляем сообщение
            producer.send(message);
            logger.warn("Sending message: " + "'Added bouquet!'");
            //закрываем соединения
            session.close();
            connection.close();

        } catch (JMSException ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        }
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
        Cookie[] cookiesArr = ((HttpServletRequest)(fc.getExternalContext().getRequest())).getCookies();
        orderId = null;
//        logger.warn("Cookies : ");
        if(cookiesArr != null && cookiesArr.length > 0) {
            for (int i = 0; i < cookiesArr.length; i++) {
                String cName = cookiesArr[i].getName();
                String cValue = cookiesArr[i].getValue();
//                logger.warn("Cookie : '" + cName + "' = " + cValue);
                if (cName.equals("orderId")) {
                    orderId = Integer.parseInt(cValue.trim());
                }
            }
        }
    }

    public void writeOrderId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Cookie cOrderId = new Cookie("orderId", "" + orderId);
        cOrderId.setMaxAge(3600);
        ((HttpServletResponse)(fc.getExternalContext().getResponse())).addCookie(cOrderId);
    }

    public void resetOrderId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Cookie cOrderId = new Cookie("orderId", "");
        cOrderId.setMaxAge(0);
        ((HttpServletResponse)(fc.getExternalContext().getResponse())).addCookie(cOrderId);
        orderId = null;
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

}
