package com.appia;

import com.appia.data.Action;
import com.appia.data.Rule;
import com.appia.data.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 7:13 PM
 */
public class UploadRules {


    /**
     * Read PoemRused.txt file
     * @throws Exception
     */
    public void LoadPoemRules() throws Exception {
        String fileName = System.getProperty("user.dir").concat("/PoemRules.txt");
        BufferedReader bufferReader = null;
        RulesSingleton rulesSingleton = RulesSingleton.getInstance();
        try {
            String ruleLine;
            bufferReader = new BufferedReader(new FileReader(fileName));
            while ((ruleLine = bufferReader.readLine()) != null) {
                AddRuleToRules(rulesSingleton, ruleLine);
            }

        } catch (FileNotFoundException fileNotFoundException) {
            throw(fileNotFoundException);
        } catch (Exception exception)   {
            throw (exception);
        }
        finally {
            try {
                if (bufferReader != null)bufferReader.close();
            } catch (Exception ex) {
                throw(ex);
            }
        }
    }

    /**
     * Add Poem Rules to RulesSingleton
     * @param rulesSingleton RulesSingleton Object
     * @param ruleLine Rule from PoemRused.txt
     */
    private void AddRuleToRules(RulesSingleton rulesSingleton,String ruleLine) {
        String[] rulesArray = ruleLine.split(": ");
        if (rulesArray.length > 0) {
            rulesSingleton.getRules().put(rulesArray[0].toString(), LoadRule(rulesArray[1].toString()));
        }
    }

    /**
     * Load rules to Rules object
     * @param commandList String of rules or commands
     * @return Rule
     */
     private Rule LoadRule(String commandList) {
         List<Object> rulesList = new ArrayList<Object>();
         for(String commands : commandList.split(" "))
         {
             if ((commands.contains("<") && commands.contains(">")) || commands.contains("$")) {
                 commands = commands.replace("<","").replace(">","");
               rulesList.add(LoadActions(commands));
             } else {
                rulesList.add(LoadWords(commands));
             }

         }
         return new Rule(rulesList);
     }

    /**
     * Load words to Word object
     * @param words String of words
     * @return Word
     */
     private Word LoadWords(String words) {
        List<String> wordList = new ArrayList<String>();
        for(String word : words.split("\\|") ) {
            wordList.add(word);
        }
        return new Word(wordList);
    }

    /**
     * Load actions to Action object
     * @param actions String of actions
     * @return Action
     */
     private Action LoadActions(String actions){
        List<String> actionList = new ArrayList<String>();
        for(String action : actions.split("\\|"))  {
            actionList.add(action);
        }
        return new Action(actionList);
    }
}
