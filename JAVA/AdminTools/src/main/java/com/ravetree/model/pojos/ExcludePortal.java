package com.ravetree.model.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent Baker on 6/24/15.
 */
public class ExcludePortal {
    private static ExcludePortal excludePortal = null;
    private List<String> portalIds = new ArrayList<>();
    private List<String> domains = new ArrayList<>();

    private ExcludePortal() {
        this.portalIds.add("540c6b6939c3d20b00066186");
        this.portalIds.add("540ca332316a360b003376fd");
        this.portalIds.add("5414f1e5f0a75c0b0088766e");
        this.portalIds.add("542f2e04ab1e870b00413577");

        this.domains.add("test.com");
        this.domains.add("demo.com");
        this.domains.add("signup.com");
        this.domains.add("ravetree.com");
    }

    public static ExcludePortal getInstance() {
        if (excludePortal == null){
            synchronized (ExcludePortal.class) {
                excludePortal = new ExcludePortal();
            }
        }
        return excludePortal;
    }

    public List<String> getPortalIds() {
        return portalIds;
    }

    public List<String> getDomains() {
        return domains;
    }
}
