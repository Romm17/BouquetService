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
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(unique = true)
    protected String login;

    @Column
    protected String password;

    @Column
    protected String role;

    public User() {
        role = getRole();
    }

    public abstract String getRole();

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
