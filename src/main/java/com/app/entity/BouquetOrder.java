package com.app.entity;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "booking")
    private List<Bouquet> bouquets;
}
