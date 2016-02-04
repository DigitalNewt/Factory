package com.appia.data;

import java.util.List;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 7:24 PM
 * Bean containing list of words.
 */
public class Word {

    private List<String> words;

    public Word(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }
}
