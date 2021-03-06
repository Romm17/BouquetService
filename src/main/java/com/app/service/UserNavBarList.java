package com.app.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents list of available link for each role
 * Created by romm on 21.11.16.
 */
@ManagedBean
@ApplicationScoped
public class UserNavBarList {

    /**
     * Map of lists of available link for each role
     */
    private final Map<String, List<NavLink> > navBarList = new HashMap<>();

    {
        List<NavLink> adminNavBar = new ArrayList<>(7);
        adminNavBar.add(new NavLink("Show Orders", "booking/showOrders.xhtml"));
        adminNavBar.add(new NavLink("Show Bouquets", "bouquets/showBouquets.xhtml"));
        adminNavBar.add(new NavLink("Add Bouquet", "bouquets/addBouquet.xhtml"));
        adminNavBar.add(new NavLink("Update Bouquet", "bouquets/updateBouquet.xhtml"));
        adminNavBar.add(new NavLink("Delete Bouquet", "bouquets/deleteBouquet.xhtml"));
        adminNavBar.add(new NavLink("Register customer", "registerCustomer.xhtml"));
        navBarList.put("Designer", adminNavBar);
        List<NavLink> customerNavBar = new ArrayList<>(2);
        customerNavBar.add(new NavLink("Buy Bouquets", "booking/bouquets.xhtml"));
        customerNavBar.add(new NavLink("Register", "registerCustomer.xhtml"));
        navBarList.put("Customer", customerNavBar);
        List<NavLink> guestNavBar = new ArrayList<>(2);
        guestNavBar.add(new NavLink("Buy Bouquets", "booking/bouquets.xhtml"));
        guestNavBar.add(new NavLink("Register", "registerCustomer.xhtml"));
        navBarList.put("", guestNavBar);
    }

    /**
     *
     * @param role
     * @return list of available link given role
     */
    public List<NavLink> getNavigationList(String role) {
        return navBarList.get(role == null ? "" : role);
    }

}
