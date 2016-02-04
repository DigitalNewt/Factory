package com.appia;

import com.appia.data.Rules;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 8:51 PM
 */
public class RulesSingleton extends Rules {

    private static RulesSingleton rulesSingleton;
    private RulesSingleton()
    {
        super();
    }

    public static synchronized RulesSingleton getInstance()
    {
        if (rulesSingleton == null)
            rulesSingleton = new RulesSingleton();

        return rulesSingleton;
    }
}
