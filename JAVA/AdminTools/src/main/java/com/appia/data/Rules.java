package com.appia.data;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 8:45 PM
 */
public class Rules {

    public final static String POEM = "POEM";
    public final static String LINE = "LINE";
    public final static String ADJECTIVE ="ADJECTIVE";
    public final static String NOUN = "NOUN";
    public final static String PRONOUN = "PRONOUN";
    public final static String VERB = "VERB";
    public final static String PREPOSITION = "PREPOSITION";
    public final static String END = "$END";
    public final static String LINEBREAK = "$LINEBREAK";

    private static Map<String, Rule> rules;

    protected Rules() {
        rules = new HashMap<String, Rule>();
    }

    public Map<String, Rule> getRules() {
        return rules;
    }
}
