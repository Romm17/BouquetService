package com.app.entity;


import javax.persistence.*;

/**
 *
 * This class represents a Customer
 * Customer can only buy bouquets
 * Created by romm on 13.11.16.
 */
@Entity
@Table(name = "customer")
public class Customer extends User{

    /**
     * Customer can have only one order
     */
    @OneToOne
    private BouquetOrder bouquetOrder;

    public String getRole() {
        return "Customer";
    }

    public BouquetOrder getBouquetOrder() {
        return bouquetOrder;
    }

    public void setBouquetOrder(BouquetOrder bouquetOrder) {
        this.bouquetOrder = bouquetOrder;
    }

    @Override
    public String toString() {
        return login;
    }
}
