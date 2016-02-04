package com.ravetree.model.bo;

import com.mongodb.Block;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.ravetree.model.dao.RavetreeDB;
import com.ravetree.model.pojos.ExcludePortal;
import com.ravetree.model.pojos.Portal;
import com.ravetree.model.util.MongoDate;
import org.bson.Document;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class PortalStats {

    private RavetreeDB db;
    private List<String> domainNames = new ArrayList<String>();
    private List<String> duplicateDomainNames = new ArrayList<String>(5);
    private List<Portal> portals = new ArrayList<Portal>();
    private static ExcludePortal excludePortal = ExcludePortal.getInstance();
    private static PortalStats portalStats = null;
    /**
     * Constructor for PortalStats
     */
    private PortalStats(RavetreeDB db) {
        this.db = db;
        final MongoCollection<Document> portalsDB = db.connectToMongo("portals");
        FindIterable<Document> iterable = portalsDB.find().sort(new Document("last_login", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String domainName = document.get("domain_name").toString();
                if (!excludePortal.getDomains().contains(domainName)) {
                    checkForDuplicatePortal(domainName);
                    domainNames.add(domainName);
                    String lastLogin = null;
                    if (document.get("last_login") != null) {
                        lastLogin = document.get("last_login").toString();
                    }
                    DateTime lastLoginDate = MongoDate.convertToDateTime(lastLogin);
                    portals.add(new Portal(document.get("_id").toString(),
                            document.get("name").toString(),
                            domainName,
                            lastLoginDate,
                            MongoDate.displayFormat(lastLoginDate),
                            document.get("creator").toString()));
                }
            }
        });
    }

    public static PortalStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload) {
            portalStats = null;
        }
        if (portalStats == null) {
            synchronized(PortalStats.class) {
                portalStats = new PortalStats(db);
            }
        }
        return portalStats;
    }

    public List<Portal> getPortals() {
        return portals;
    }

    /**
     * Find the total number of portals
     *
     * @return total portals
     */
    public long totalPortals() {
        return domainNames.size();
    }

    /**
     * Check to see if there are any duplicate domain name in portal
     *
     * @return List of duplicate domain names
     */
    public List<String> getDuplicateDomainNames() {
        return duplicateDomainNames;
    }


    public long getAverageUsersPerPortal() {
        long count = domainNames.size();
        long userCount = 0;
        for(Portal portal: portals) {
            userCount += portal.getUsers().size();
        }
        return Math.round(userCount / count);

    }
    /**
     * Check to see if domain name is a duplicate
     *
     * @param domainName domain name
     */
    private void checkForDuplicatePortal(String domainName) {
        if (domainNames.contains(domainName)) {
            if (!duplicateDomainNames.isEmpty()) {
                duplicateDomainNames.add(domainName);
            } else {
                addToDuplicateDomainNames(domainName);
            }
        }
    }

    /**
     * Add duplicate portal domain name to duplicate list.
     *
     * @param domainName domain name
     */
    private void addToDuplicateDomainNames(String domainName) {
        if (!duplicateDomainNames.isEmpty()) {
            if (!duplicateDomainNames.contains(domainName)) {
                duplicateDomainNames.add(domainName);
            }
        } else {
            duplicateDomainNames.add(domainName);
        }
    }


}
