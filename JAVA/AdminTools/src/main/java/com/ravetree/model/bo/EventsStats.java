package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class EventsStats {

    private static final Logger logger = LoggerFactory.getLogger(EventsStats.class);
    private RavetreeDB db;
    private static EventsStats eventsStats = null;
    private long totalEvents;

    private EventsStats(RavetreeDB db) {
        this.db = db;
        this.totalEvents = db.connectToMongo("events").count();
    }

    public static EventsStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            eventsStats = null;
        }
        if (eventsStats == null) {
            synchronized(EventsStats.class) {
                eventsStats = new EventsStats(db);
            }
        }
        return eventsStats;
    }

    public long getTotalEvents() {
        return totalEvents;
    }
}
