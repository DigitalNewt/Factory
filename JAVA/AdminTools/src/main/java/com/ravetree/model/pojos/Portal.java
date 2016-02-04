package com.ravetree.model.pojos;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent Baker on 6/17/15.
 */
public class Portal { //implements Comparable<Portal> {
    private String portalId;
    private String name;
    private String domainName;
    private List<User> users;
    private DateTime lastLogin;
    private String displayDate;
    private User creator;
    private String creatorId;

    public Portal(String portalId, String name, String domainName, DateTime lastLogin, String displayDate, String creatorId) {
        this.portalId = portalId;
        this.name = name;
        this.domainName = domainName;
        this.users = new ArrayList<User>();
        this.lastLogin = lastLogin;
        this.displayDate = displayDate;
        this.creatorId = creatorId;
    }

    public String getPortalId() {
        return portalId;
    }

    public String getName() {
        return name;
    }

    public String getDomainName() {
        return domainName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public String getDisplayDate() { return displayDate; }

    public User getCreator() { return creator; }

    public void setCreator(User creator) { this.creator = creator; }

    public String getCreatorId() { return creatorId; }

//    public int compareTo(Portal portal) {
//
//        return this.lastLogin.compareTo(portal.getLastLogin());
//    }
//
//    public static Comparator<Portal> PortalLastLoginComparator
//            = new Comparator<Portal>() {
//
//        public int compare(Portal portal1, Portal portal2) {
//
//            //ascending order
//            return portal1.getLastLogin().compareTo(portal2.getLastLogin());
//
//            //descending order
//            //return fruitName2.compareTo(fruitName1);
//        }
//
//    };
}
