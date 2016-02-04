package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class ProjectStats {

    private RavetreeDB db;
    private static ProjectStats projectStats = null;
    private long totalProjects;

    private ProjectStats(RavetreeDB db) {
        this.db = db;
        this.totalProjects = db.connectToMongo("projects").count();
    }

    public static ProjectStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            projectStats = null;
        }
        if (projectStats == null) {
            synchronized(ProjectStats.class) {
                projectStats = new ProjectStats(db);
            }
        }
        return projectStats;
    }

    public long getTotalProjects() {
        return totalProjects;
    }
}
