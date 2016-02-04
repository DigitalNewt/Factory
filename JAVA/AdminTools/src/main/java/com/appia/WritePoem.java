package com.appia;

import com.appia.data.Action;
import com.appia.data.Rule;
import com.appia.data.Rules;
import com.appia.data.Word;

import java.util.List;
import java.util.Random;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 10:23 PM
 */
public class WritePoem {


    private RulesSingleton rulesSingleton = RulesSingleton.getInstance();
    private String poem = "";

    /**
     * Generate Poem
     * @return Poem as String
     */
    public String generatePoem() {
        for(Object actions: rulesSingleton.getRules().get(Rules.POEM).getRules()) {
            if(actions.getClass().getName().equals(Rule.ACTION)) {
                Action actionBean = (Action) actions;
                String action = actionBean.getActions().get(0).toString();
                processActions(action);
            }
        }
        return poem;
    }

    /**
     * Process Action in RulesSingleton
     * @param action Action to process
     */
    private void processActions(String action) {
        List<Object> rules = rulesSingleton.getRules().get(action).getRules();
        for(Object actions: rules) {
            if(actions.getClass().getName().equals(Rule.ACTION)) {
                Action actionBean = (Action) actions;
                String actionName = randomItemFromList(actionBean.getActions());
                if (!actionName.equals(Rules.END) && !actionName.equals(Rules.LINEBREAK)) {
                    processActions(actionName);
                } else if(actionName.equals(Rules.LINEBREAK)) {
                    poem += "\n";
                }
            } else if(actions.getClass().getName().equals(Rule.WORD)){
                Word word = (Word) actions;
                poem += randomItemFromList(word.getWords()).concat(" ");
            }
        }
    }

    /**
     * Choose a random item from list.
     * @param list of words
     * @return Word as String
     */
    private String randomItemFromList(List<String> list) {
        Random random = new Random();
        int min = 0;
        int max = list.size() - 1;
        int randomNum = random.nextInt((max - min) + 1) + min;

        return list.get(randomNum).toString();
    }

}
