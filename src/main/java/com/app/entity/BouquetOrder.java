package com.app.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by romm on 13.11.16.
 */
@Entity
@Table(name = "bouquetOrder")
public class BouquetOrder {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Customer customer;

    @Column
    private BouquetOrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking")
    private List<Bouquet> bouquets;

    public void addBouquet(Bouquet bouquet) {
        bouquets.add(bouquet);
    }

    public void removeBouquet(Bouquet bouquet) {
        for (int i = 0; i < bouquets.size(); i++) {
            if (Objects.equals(bouquets.get(i).getId(), bouquet.getId())) {
                bouquets.remove(i);
                i--;
            }
        }
    }

    public BouquetOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Bouquet> getBouquets() {
        return bouquets;
    }

    public void setBouquets(List<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    public BouquetOrderStatus getStatus() {
        return status;
    }

    public void setStatus(BouquetOrderStatus status) {
        this.status = status;
    }
}
