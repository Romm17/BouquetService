package com.app.service;

import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by romm on 21.11.16.
 */
@ManagedBean
public class LogoutService {

    public LogoutService() {

    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect("/BouquetService_war_exploded/booking/bouquets.xhtml");
    }

    public void login() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/BouquetService_war_exploded/booking/showOrders.xhtml");
    }

    public void logoutOrLogin(String username) throws IOException {
        if (username == null || username.equals("")) {
            login();
        }
        else {
            logout();
        }
    }
}
