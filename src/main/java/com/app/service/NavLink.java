package com.app.service;

/**
 * Created by romm on 21.11.16.
 */
public class NavLink {

    private String title;
    private String ref;

    public NavLink() {
    }

    public NavLink(String title, String ref) {
        this.title = title;
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
