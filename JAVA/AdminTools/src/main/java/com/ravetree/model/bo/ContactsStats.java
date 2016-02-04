package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class ContactsStats {

    private static final Logger logger = LoggerFactory.getLogger(ContactsStats.class);
    private RavetreeDB db;
    private static ContactsStats contactsStats = null;
    private long totalContacts;

    private ContactsStats(RavetreeDB db) {
        this.db = db;
        this.totalContacts = db.connectToMongo("contacts").count();
    }

    public static ContactsStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            contactsStats = null;
        }
        if (contactsStats == null) {
            synchronized(ContactsStats.class) {
                contactsStats = new ContactsStats(db);
            }
        }
        return contactsStats;
    }

    public long getTotalContacts() {
        return totalContacts;
    }
}
