package com.ravetree.model.dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Brent Baker on 6/10/15.
 */
public class RavetreeDBTests {

    @Test
    public void testConnectToMongo() {

        long count = 0;
        RavetreeDB db = new RavetreeDB();
        count = db.connectToMongo("portals").count();

        assertTrue(count > 0);
    }
}
