package com.appia.data;

import java.util.List;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 8:37 PM
 */
public class Rule {
    public final static String ACTION = "com.appia.data.Action";
    public final static String WORD = "com.appia.data.Word";
    private List<Object> rules;

    public Rule(List<Object> rules) {
        this.rules = rules;
    }

    public List<Object> getRules() {
        return rules;
    }

}
