package com.appia;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.appia.data.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 7:13 PM
 */
public class LoadRulesTest {

    /**
     * Test setup
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        UploadRules uploadRules = new UploadRules();
        uploadRules.LoadPoemRules();
    }

    /**
     * Test loading words to RulesSingleton
     */
    @Test
    public void loadWords() {
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        Word word = (Word) rulesSingleton.getRules().get(Rules.ADJECTIVE).getRules().get(0);
        assertNotNull(word);
        assertEquals(word.getWords().size(), 8);
    }

    /**
     * Test loading actions to RulesSingleton
     */
    @Test
    public void loadActions() {
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        Action action = (Action) rulesSingleton.getRules().get(Rules.VERB).getRules().get(1);
        assertNotNull(action);
        assertEquals(action.getActions().size(), 3);
    }

    /**
     * Test loading rules to RulesSingleton
     */
    @Test
    public void loadRules() {
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        Rule rule = rulesSingleton.getRules().get(Rules.VERB);
        assertNotNull(rule);
        assertEquals(rule.getRules().size(), 2);
        assertTrue(rule.getRules().get(0).getClass().getName().toString().contains(Rule.WORD));
        assertTrue(rule.getRules().get(1).getClass().getName().toString().contains(Rule.ACTION));
    }

    /**
     * Test adding rules to RulesSingleton
     */
    @Test
    public void addRuleToRules() {
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        Action action = (Action) rulesSingleton.getRules().get(Rules.LINE).getRules().get(1);
        assertTrue(action.getActions().get(0).toString().equals(Rules.LINEBREAK));
    }

    /**
     * Test random rules generator
     */
    @Test
    public void randomGenerator() {
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        Rule rule = rulesSingleton.getRules().get(Rules.VERB);
        assertNotNull(rule);
        assertEquals(rule.getRules().size(), 2);
        assertTrue(rule.getRules().get(0).getClass().getName().toString().contains(Rule.WORD));
        Word word = (Word) rule.getRules().get(0);
        Random random = new Random();
        int min = 0;
        int max = word.getWords().size() - 1;

        int randomNum = random.nextInt((max - min) + 1) + min;
        System.out.print("\nMin Number:" + min);
        System.out.print("\nMax Number:" + max);
        System.out.print("\nRandom Number:" + randomNum);
        System.out.print("\nRandom Word:" + word.getWords().get(randomNum).toString());
    }

    /**
     * Test poem generator
     */
    @Test
    public void generatePoem() {
        WritePoem writePoem = new WritePoem();
        System.out.print("\n" + writePoem.generatePoem());
    }



}
