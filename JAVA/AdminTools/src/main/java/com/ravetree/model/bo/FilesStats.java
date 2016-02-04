package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class FilesStats {

    private static final Logger logger = LoggerFactory.getLogger(FilesStats.class);
    private RavetreeDB db;

    public FilesStats(RavetreeDB db) {
        this.db = db;
    }

    public long totalFiles() {
        return db.connectToMongo("files").count();
    }

}
