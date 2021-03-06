package com.app.service;

import com.app.entity.Customer;
import com.app.entity.User;
import com.app.repository.CustomerDAO;
import com.app.repository.UserDAO;

import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * This class represents all methods application need to manage Users
 * Created by romm on 21.11.16.
 */
@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserService {

    /**
     * DAO to access entities
     */
    @EJB
    private CustomerDAO customerDAO;

    /**
     * DAO to access entities
     */
    @EJB
    private UserDAO userDAO;

    private String login;
    private String pass;

    public UserService() {

    }

    public void registerCustomer() throws NoSuchAlgorithmException {
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(LoginService.getEncodedPassword(pass));
        customerDAO.create(customer);
    }

    public User getUserByLogin(String login) {
        List<User> users = userDAO.getAll();
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
