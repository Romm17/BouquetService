package com.app.entity;

import javax.persistence.*;

/**
 *
 * This class represents category of bouquet
 * Created by romm on 12.11.16.
 */
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    public Section() {
    }

    public Section(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
