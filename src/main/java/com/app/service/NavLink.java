package com.app.service;

/**
 * Class for store hypertext name and reference together
 * Created by romm on 21.11.16.
 */
public class NavLink {

    /**
     * Link title
     */
    private String title;

    /**
     * Link reference
     */
    private String ref;

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
