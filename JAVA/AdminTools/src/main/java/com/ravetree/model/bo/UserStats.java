package com.ravetree.model.bo;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.ravetree.model.dao.RavetreeDB;
import com.ravetree.model.pojos.ExcludePortal;
import com.ravetree.model.pojos.Portal;
import com.ravetree.model.pojos.User;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class UserStats {

    private RavetreeDB db;
    private List<User> users = new ArrayList<>();
    private static ExcludePortal excludePortal = ExcludePortal.getInstance();
    private static UserStats userStats = null;

    private UserStats(final RavetreeDB db) {
        this.db = db;
        final MongoCollection<Document> portalsDB = db.connectToMongo("users");
        FindIterable<Document> iterable = portalsDB.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String portalId = null;
                if (document.get("portal_id") != null) {
                    portalId = document.get("portal_id").toString();
                }
                if (!excludePortal.getPortalIds().contains(portalId)) {
                    User user = new User(document.get("_id").toString(),
                            document.get("name").toString(),
                            document.getDate("last_login"),
                            portalId,
                            document.getString("email").toString());
                    users.add(user);
                    addUserToPortalStats(PortalStats.getInstance(db, false), user);
                }
            }
        });
    }

    public static UserStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            userStats = null;
        }
        if (userStats == null) {
            synchronized(UserStats.class) {
                userStats = new UserStats(db);
            }
        }
        return userStats;
    }

    public long totalUsers() {
        return users.size();
    }


    private void addUserToPortalStats(PortalStats portalStats, User user) {
        if (user.getPortal() != null) {
            for (Portal portal : portalStats.getPortals()) {
                if (portal.getCreatorId().equals(user.getUserId())) {
                    portal.setCreator(user);
                }
                if (portal.getPortalId().equals(user.getPortal())) {
                    portal.getUsers().add(user);
                    break;
                }
            }
        }
    }

}
