package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class WorkItemsStats {

    private static final Logger logger = LoggerFactory.getLogger(WorkItemsStats.class);
    private RavetreeDB db;
    private static WorkItemsStats workItemsStats = null;
    private long totalWorkItems;

    private WorkItemsStats(RavetreeDB db) {
        this.db = db;
        totalWorkItems = db.connectToMongo("workitems").count();
    }

    public static WorkItemsStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            workItemsStats = null;
        }
        if (workItemsStats == null) {
            synchronized(WorkItemsStats.class) {
                workItemsStats = new WorkItemsStats(db);
            }
        }
        return workItemsStats;
    }

    public long getTotalWorkItems() {
        return totalWorkItems;
    }
}
