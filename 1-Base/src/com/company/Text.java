package com.company;

import java.util.*;
/**
 * Class containing all the fields and methods needed for text to be analyzed.
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class Text {
    /**
     * A private field holding string (text) given by user
     * */
    private String string;
    /**
     * A private int field holding number of words.
     * */
    private int numberOfWords;
    /**
     * A private int field holding number of characters.
     * */
    private int numberOfChars;
    /**
     * A private field holding array of words.
     * */
    private String[] words;
    /**
     * A private field holding palindromes. Its type is Map(String - palindrome, Integer - number of occurrence).
     * */
    private final Map<String, Integer> palindromes = new LinkedHashMap<>();
    /**
     * A private field holding letter. Its type is Map(Integer - number of letters in the word, Integer - number of words
     * with that many letters).
     * */
    private final Map<Integer, Integer> letters = new TreeMap<>();
    /**
     * A private field holding diacritics. Its type is Map(Character - diacritical mark, Integer - number of occurrence).
     * */
    private final Map<Character, Integer> diacritics = new TreeMap<>();
    /**
     * A public method setting private fields in this class by running setting methods of this class: splitSentence(),
     * setNumberOfChars(), setNumberOfWords(), setPalindromes(), setLetters(), setDiacritics(). The try block covers
     * if-else block. If there is no input text the exception is thrown - NoInputTextException() - else private fields
     * are initialized. The catch block runs a View method for printing out exception message.
     * @param str a string that was typed in by user
     * @param view a object of the View class needed for writing the message for exception in console.
     * */
    public void setFields(String str, View view){
        try {
            if(str.equals(""))
                throw new NoInputTextException();
            else {
                this.string = str;
                splitSentence();
                setNumberOfChars();
                setNumberOfWords();
                setPalindromes();
                setLetters();
                setDiacritics();
            }
        }
        catch (NoInputTextException e){
            view.exception();
        }
    }
    /**
     * Public method enabling getting the String stored in the private string field.
     * @return - value of string field
     * */
    public String getString(){
        return this.string;
    }
    /**
     * Public method enabling getting the array of words stored in the private String[] field.
     * @return - words array
     * */
    public String[] getWords() {
        return words;
    }
    /**
     * Private method setting the numberOfWords field, which is private and its type is int.
     */
    private void setNumberOfWords() {
        this.numberOfWords = words.length;
    }
    /**
     * Public method enabling getting the number of words stored in the private int field.
     * @return - number of words
     * */
    public int getNumberOfWords() {
        return this.numberOfWords;
    }
    /**
     * Private method setting the numberOfChars field, which is private and its type is int.
     */
    private void setNumberOfChars() {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ')
                count++;
        }

        this.numberOfChars = count;
    }
    /**
     * Public method enabling getting the number of characters stored in the private int field.
     * @return - number of characters
     * */
    public int getNumberOfChars() {
        return this.numberOfChars;
    }
    /**
     * Private method setting the words field, which is private, by splitting the sentence using regex.
     */
    private void splitSentence() {
        if (string != null) {
            words = string.split("[\\s,:;!()?.\\[\\]]+");
        }
    }
    /**
     * Public method counting the number of times the last character is repeated in this text
     * @return - number of last characters
     * */
    public int countLastChar(){
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.toLowerCase().charAt(i) == string.toLowerCase().charAt(string.length()-1))
                count++;
        }
        return count;
    }
    /**
     * Private method setting the letter field, which is private and its type is Map(Integer - number of letters,
     * Integer - number of words with that many letters)
     */
    private void setLetters(){
        for (String i : words) {
            int n = i.length();
            boolean done = false;
            if (!letters.isEmpty()) {
                Set<Integer> setKey = letters.keySet();
                for (Integer l : setKey) {
                    if (l == n) {
                        int count = letters.get(l);
                        letters.remove(l);
                        letters.put(n, count + 1);
                        done = true;
                        break;
                    }
                }
            }
            if (!done) {
                letters.put(n, 1);
            }
        }
    }
    /**
     * Public method enabling getting data stored in letters field.
     * @return - Map (Integer - number of letters, Integer - number of words with that many letters)
     * */
    public Map<Integer, Integer> getLetters() {
        return this.letters;
    }
    /**
     * Private method setting the palindromes field, which is private and its type is Map(String - palindrome, Integer
     * - number of its occurrence in the text ).
     */
    private void setPalindromes() {
        for (String i : words) {
            int k = i.length();
            if (k > 1) {
                boolean is = true;
                for (int j = 0; j < i.length(); j++) {
                    if (i.toLowerCase().charAt(j) != i.toLowerCase().charAt(k - 1)) {
                        is = false;
                    }
                    k--;
                }
                if (is) {
                    boolean done = false;
                    if (!palindromes.isEmpty()) {
                        Set<String> setKey = palindromes.keySet();
                        for (String l : setKey) {
                            if (i.toLowerCase().equals(l.toLowerCase())) {
                                int count = palindromes.get(l);
                                palindromes.remove(l);
                                palindromes.put(i, count + 1);
                                done = true;
                                break;
                            }
                        }
                    }
                    if (!done) {
                        palindromes.put(i, 1);
                    }
                }
            }
        }
    }
    /**
     * Public method enabling getting data stored in palindromes field.
     * @return - Map (String - palindrome, Integer - number of its occurrence in the text ).
     * */
    public Map<String, Integer> getPalindromes() {
        return this.palindromes;
    }
    /**
     * Private method setting the diacritics field, which is private and its type is Map(Character - diacritical mark,
     * Integer - number of occurrence)
     */
    private void setDiacritics(){
        for (int i = 0; i < string.length(); i++) {
            if ((int)string.charAt(i) > 191 && (int)string.charAt(i) < 384 ){
                boolean done = false;
                if (!diacritics.isEmpty()) {
                    Set<Character> setKey = diacritics.keySet();
                    for (Character l : setKey) {
                        if (l.equals(string.charAt(i))) {
                            int count = diacritics.get(l);
                            diacritics.remove(l);
                            diacritics.put(l, count + 1);
                            done = true;
                            break;
                        }
                    }
                }
                if (!done) {
                    diacritics.put(string.charAt(i), 1);
                }
            }
        }
    }
    /**
     * Public method enabling getting data stored in diacritics field.
     * @return - Map (Character - diacritical mark, Integer - number of occurrence)
     * */
    public Map<Character, Integer> getDiacritics() {
        return diacritics;
    }
}
