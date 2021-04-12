package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class containing all the fields and methods needed for text to be analyzed.
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class Model {

    /**
     * A private field holding palindromes. Its type is Map(String - palindrome, Integer - number of occurrence).
     * A map collection was used in the first laboratory, so it needn't to be changed.
     */
    private static final Map<String, Integer> palindromes = new LinkedHashMap<>();
    /**
     * A private field holding letter. Its type is Map(Integer - number of letters in the word, Integer - number of words
     * with that many letters).
     * A map collection was used in the first laboratory, so it needn't to be changed.
     */
    private static final Map<Integer, Integer> letters = new TreeMap<>();
    /**
     * A private field holding diacritics. Its type is Map(Character - diacritical mark, Integer - number of occurrence).
     * A map collection was used in the first laboratory, so it needn't to be changed.
     */
    private static final Map<Character, Integer> diacritics = new TreeMap<>();
    /**
     * A private field holding string (text) given by user
     */
    private static String string;
    /**
     * A private int field holding number of words.
     */
    private static int numberOfWords;
    /**
     * A private int field holding number of characters.
     */
    private static int numberOfChars;
    /**
     * A private field holding list of words. Before that was an array, which was supposed to be changed to collection.
     */
    private static List<String> words = new ArrayList<>();

    /**
     * A public method setting private fields in this class by running setting methods of this class: splitSentence(),
     * setNumberOfChars(), setNumberOfWords(), setPalindromes(), setLetters(), setDiacritics().
     *
     * @param str a string that was typed in by user
     */
    public void setFields(String str) {
        string = str;
        setWords();
        setNumberOfChars();
        setNumberOfWords();
        setPalindromes();
        setLetters();
        setDiacritics();
    }

    /**
     * Public method enabling getting the String stored in the private string field.
     *
     * @return - value of string field
     */
    public String getString() {
        return string;
    }

    /**
     * Public method enabling getting the list of words stored in the private List field.
     *
     * @return - words array
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * Private method setting the numberOfWords field, which is private and its type is int.
     */
    private void setNumberOfWords() {
        numberOfWords = words.size();
    }

    /**
     * Public method enabling getting the number of words stored in the private int field.
     *
     * @return - number of words
     */
    public int getNumberOfWords() {
        return numberOfWords;
    }

    /**
     * Private method setting the numberOfChars field, which is private and its type is int. It is running
     * countChars method.
     */
    private void setNumberOfChars() {
        numberOfChars = countChars();
    }

    /**
     * Private method counting the number of characters in the text
     *
     * @return - number of characters
     */
    private int countChars() {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ')
                count++;
        }
        return count;
    }

    /**
     * Public method enabling getting the number of characters stored in the private int field.
     *
     * @return - number of characters
     */
    public int getNumberOfChars() {
        return numberOfChars;
    }

    /**
     * Private method setting the words field, which is private, by splitting the sentence using regex
     * and stream.
     *
     * @return separated string
     */
    private List<String> splitSentence() {
        return Stream
                .of(string.split("[\\s,:;!()?.\\[\\]]+"))
                .collect(Collectors.toList());
    }

    /**
     * Private method setting the words field, which is private, by running splitSentence method.
     */
    private void setWords() {
        words = splitSentence();
    }

    /**
     * Public method counting the number of times the last character is repeated in this text
     *
     * @return - number of last characters
     */
    public int countLastChar() {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.toLowerCase().charAt(i) == string.toLowerCase().charAt(string.length() - 1))
                count++;
        }
        return count;
    }

    /**
     * Private method setting the letter field, which is private and its type is Map(Integer - number of letters,
     * Integer - number of words with that many letters)
     */
    private void setLetters() {
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
     *
     * @return - Map (Integer - number of letters, Integer - number of words with that many letters)
     */
    public Map<Integer, Integer> getLetters() {
        return letters;
    }

    /**
     * Private method setting the palindromes field, which is private and it is a collection - its type is
     * Map(String - palindrome, Integer - number of its occurrence in the text ). In this method for-each loop
     * and stream were used.
     */
    private void setPalindromes() {
        List<String> stream = words.stream()
                .filter(w -> w.length() > 1)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        for (String i : stream) {
            int k = i.length();
            boolean is = true;
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) != i.charAt(k - 1)) {
                    is = false;
                }
                k--;
            }
            if (is) {
                boolean done = false;
                if (!palindromes.isEmpty()) {
                    Set<String> setKey = palindromes.keySet();
                    for (String l : setKey) {
                        if (i.equals(l)) {
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

    /**
     * Public method enabling getting data stored in palindromes field.
     *
     * @return - Map (String - palindrome, Integer - number of its occurrence in the text ).
     */
    public Map<String, Integer> getPalindromes() {
        return palindromes;
    }

    /**
     * Private method setting the diacritics field, which is private and its type is Map(Character - diacritical mark,
     * Integer - number of occurrence)
     */
    private void setDiacritics() {
        for (int i = 0; i < string.length(); i++) {
            if ((int) string.charAt(i) > 191 && (int) string.charAt(i) < 384) {
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
     *
     * @return - Map (Character - diacritical mark, Integer - number of occurrence)
     */
    public Map<Character, Integer> getDiacritics() {
        return diacritics;
    }

    /**
     * Public method clearing all fields of the Model class
     */
    public void clear() {
        string = null;
        numberOfChars = 0;
        numberOfWords = 0;
        words.clear();
        palindromes.clear();
        letters.clear();
        diacritics.clear();
    }
}