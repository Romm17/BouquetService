package com.app.entity;


import javax.persistence.*;

/**
 * Created by romm on 13.11.16.
 */
@Entity
@Table(name = "customer")
public class Customer extends User{

    @OneToOne
    private BouquetOrder bouquetOrder;

    public Customer() {
    }

    public BouquetOrder getBouquetOrder() {
        return bouquetOrder;
    }

    public void setBouquetOrder(BouquetOrder bouquetOrder) {
        this.bouquetOrder = bouquetOrder;
    }

}