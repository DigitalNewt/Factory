package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class PostsStats {

    private static final Logger logger = LoggerFactory.getLogger(PostsStats.class);
    private long totalPost;
    private RavetreeDB db;
    private static PostsStats postsStats = null;

    private PostsStats(RavetreeDB db) {
        this.db = db;
        this.totalPost = db.connectToMongo("posts").count();
    }

    public static PostsStats getInstance(RavetreeDB db, Boolean reload){
        if (reload) {
            postsStats = null;
        }
        if (postsStats == null) {
            synchronized (PostsStats.class) {
                postsStats = new PostsStats(db);
            }
        }
        return  postsStats;
    }

    public long getTotalPost() {
        return totalPost;
    }
}
