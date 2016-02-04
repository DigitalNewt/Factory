package com.ravetree.model.pojos;

import java.util.Date;
import java.util.List;

/**
 * Created by Brent Baker on 6/19/15.
 */
public class User {
    private String userId;
    private String name;
    private Date lastLogin;
    private String portal;
    private String email;


    public User(String userId, String name, Date lastLogin, String portal, String email) {
        this.userId = userId;
        this.name = name;
        this.lastLogin = lastLogin;
        this.portal = portal;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public String getPortal() {
        return portal;
    }

    public String getEmail() { return email; }
}
