package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by romm on 13.11.16.
 */
@Entity
@Table(name = "designer")
public class Designer extends User {

    @Override
    public String getRole() {
        return "Designer";
    }

}
