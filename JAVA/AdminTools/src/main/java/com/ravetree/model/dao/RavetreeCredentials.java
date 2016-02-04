package com.ravetree.model.dao;

import java.util.Map;

/**
 * Created by Brent Baker on 6/14/15.
 */
public class RavetreeCredentials {
    private static RavetreeCredentials firstInstance = null;

    static boolean firstThread = true;

    private String mdbName;
    private String mdbAddress;
    private String mdbUser;
    private String mdbPass;
    private int mdbPort;

    private RavetreeCredentials() {
        Map<String, String> env = System.getenv();
        this.mdbName = env.get("MDB_NAME");
        this.mdbAddress = env.get("MDB_ADDRESS");
        this.mdbUser = env.get("MDB_USER");
        this.mdbPass = env.get("MDB_PASS");
        this.mdbPort = Integer.parseInt(env.get("MDB_PORT"));
    }

    public static RavetreeCredentials getInstance() {
        if (firstInstance == null) {
            if (firstThread) {
                firstThread = false;
                // Here we just use synchronized when the first object
                // is created
                synchronized (RavetreeCredentials.class) {
                    if (firstInstance == null) {
                        // If the instance isn't needed it isn't created
                        // This is known as lazy instantiation
                        firstInstance = new RavetreeCredentials();
                    }
                }
            }
        }

        return firstInstance;
    }

    public String getMdbName() {
        return mdbName;
    }

    public String getMdbAddress() {
        return mdbAddress;
    }

    public String getMdbUser() {
        return mdbUser;
    }

    public String getMdbPass() {
        return mdbPass;
    }

    public int getMdbPort() { return mdbPort; }
}
