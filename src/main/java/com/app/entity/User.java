package com.app.entity;

import javax.persistence.*;

/**
 * Created by romm on 13.11.16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;

    @Column
    protected String login;

    @Column
    protected String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
