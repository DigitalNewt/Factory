package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class PortalStatsTests {

    private PortalStats portalStats;

    @Before
    public void setUp() {
        portalStats = PortalStats.getInstance(new RavetreeDB(), false);
    }

    @Test
    public void testTotalPortals() {
        long totals = portalStats.totalPortals();
        assertTrue(totals > 0);
    }

    @Test
    public void testDuplicatePortals() {
        assertNotNull(portalStats.getDuplicateDomainNames());

    }

}
