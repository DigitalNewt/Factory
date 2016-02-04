package com.ravetree.model.bo;

import com.ravetree.model.dao.RavetreeDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brent Baker on 6/16/15.
 */
public class AccountsStats {

    private RavetreeDB db;
    private static AccountsStats accountsStats = null;
    private long totalAccounts;
    private AccountsStats(RavetreeDB db) {
        this.db = db;
        this.totalAccounts = db.connectToMongo("accounts").count();
    }

    public static AccountsStats getInstance(RavetreeDB db, Boolean reload) {
        if (reload){
            accountsStats = null;
        }
        if (accountsStats == null) {
            synchronized(AccountsStats.class) {
                accountsStats = new AccountsStats(db);
            }
        }
        return accountsStats;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }
}
