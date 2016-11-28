package com.app.service;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * This class represents method to manage user session
 * Created by romm on 21.11.16.
 */
@ManagedBean
public class LogoutService {

    /**
     * logout method
     * @throws IOException
     */
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect("/BouquetService_war_exploded/booking/bouquets.xhtml");
    }

    /**
     * Method for redirect after login
     * @throws IOException
     */
    public void login() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/BouquetService_war_exploded/booking/showOrders.xhtml");
    }

    /**
     * login if user unauthorized and logout otherwise
     * @param username user login
     * @throws IOException
     */
    public void logoutOrLogin(String username) throws IOException {
        if (username == null || username.equals("")) {
            login();
        }
        else {
            logout();
        }
    }
}
