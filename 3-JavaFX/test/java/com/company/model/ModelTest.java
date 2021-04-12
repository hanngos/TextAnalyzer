package com.company.model;

import com.company.view.NoInputTextException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for running tests on Model class' methods.
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
class ModelTest {

    private final Model text = new Model();

    @BeforeEach
    void clear(){
        text.clear();
    }

    /**Parametrized test for checking correctness of throwing and catching NoInputException in public setFields
     * method of Model class.
     * @param input - exemplary input strings
     * */
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "", "      "})
    void testNoInputException(String input) {
        try {
            if (input.isBlank()) throw new NoInputTextException();
            else text.setFields(input);
        } catch (Exception e) {
            assertEquals("Exception! No text was typed in!", e.getMessage());
        }
    }

    /**Parametrized test for checking correctness of assigning string in public setFields method of Model class.
     * @param  argumentsAccessor exemplary input strings
     * */
    @ParameterizedTest
    @CsvSource({"cat, cat", "Santa Claus is coming to town, Santa Claus is coming to town", ". !!! ? () ::, . !!! ? () ::", "ą, ą"})
    void setFieldsTestString(ArgumentsAccessor argumentsAccessor) {
        if (!argumentsAccessor.getString(0).isBlank()) {
            text.setFields(argumentsAccessor.getString(0));
            assertEquals(argumentsAccessor.getString(0), text.getString());
        }
    }
    /**Parametrized test for checking correctness of assigning number of words
     * @param  input exemplary input strings
     * @param expectedNumber expected number of words
     * */
    @ParameterizedTest
    @MethodSource("setFieldsTestNumberOfWordsArgs")
    void setFieldsTestNumberOfWords(String input, int expectedNumber) {
        text.setFields(input);
        assertEquals(expectedNumber, text.getNumberOfWords());
    }

    /** Arguments for setFieldsTestNumberOfWords test
     * @return stream of arguments to setFieldsTestNumberOfWords test
     * */
    private static Stream<Arguments> setFieldsTestNumberOfWordsArgs() {
        return Stream.of(
                Arguments.of("text", 1),
                Arguments.of("kitty cat", 2),
                Arguments.of("Santa Claus is coming to town?", 6),
                Arguments.of("Ala? Czy masz koteła? ! ?(Hmm. Hmm?)", 6),
                Arguments.of(".", 0),
                Arguments.of(". !!! ? () ::", 0),
                Arguments.of("text. !!! ? () ::", 1)
        );
    }

    /**Parametrized test for checking correctness of assigning number of chars
     * @param input exemplary input strings
     * @param expectedNumber expected number of chars
     **/
    @ParameterizedTest
    @MethodSource("setFieldsTestNumberOfCharsArgs")
    void setFieldsTestNumberOfChars(String input, int expectedNumber) {
        text.setFields(input);
        assertEquals(expectedNumber, text.getNumberOfChars());
    }

    /** Arguments for setFieldsTestNumberOfChars test
     * @return stream of arguments to setFieldsTestNumberOfChars test
     * */
    private static Stream<Arguments> setFieldsTestNumberOfCharsArgs() {
        return Stream.of(
                Arguments.of("text", 4),
                Arguments.of("kitty cat", 8),
                Arguments.of("Santa Claus is coming to town?", 25),
                Arguments.of("Ala? Czy masz koteła? ! ?(Hmm. Hmm?)", 30),
                Arguments.of(".", 1),
                Arguments.of(". !!! ? () ::", 9),
                Arguments.of("text. !!! ? (ł) ::", 14),
                Arguments.of("", 0)
        );
    }

    /**Parametrized test for checking correctness of assigning words
     * @param words expected list of words
     * @param input exemplary input strings
     * */
    @ParameterizedTest
    @MethodSource("setFieldsTestWordsArgs")
    void setFieldsTestWords(List<String> words, String input) {
        if (!input.isBlank()) {
            text.setFields(input);
            assertEquals(words, text.getWords());
        }
    }

    /** Arguments for setFieldsTestWords test
     * @return stream of arguments to setFieldsTestWords test
     * */
    private static Stream<Arguments> setFieldsTestWordsArgs() {
        List<Arguments> listOfArguments = new ArrayList<>();
        listOfArguments.add(Arguments.of(generateTestList("text"), "text"));
        listOfArguments.add(Arguments.of(generateTestList("Santa", "Claus", "is", "coming", "to", "town"), "Santa Claus is coming to town?"));
        listOfArguments.add(Arguments.of(generateTestList("Ala", "Czy", "masz", "koteła", "Hmm", "Hmm"), "Ala? Czy masz koteła? ! ?(Hmm. Hmm?))"));
        listOfArguments.add(Arguments.of(generateTestList(), ". !!! ? () ::"));
        listOfArguments.add(Arguments.of(generateTestList(), ""));
        return listOfArguments.stream();
    }

    /**Private method with variable number of arguments generating list from this args.
     * @param args exemplary input strings to generate list
     * @return list of given args
     * */
    private static List<String> generateTestList(String... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    /**Parametrized test for checking correctness of assigning palindromes
     * @param palindromes expected map of palindromes
     * @param input exemplary input strings
     */
    @ParameterizedTest
    @MethodSource("setFieldsTestPalindromesArgs")
    void setFieldsTestPalindromes(HashMap<String, Integer> palindromes, String input) {
        text.setFields(input);
        assertEquals(palindromes, text.getPalindromes());
    }

    /** Arguments for setFieldsTestPalindromes test
     * @return stream of arguments to setFieldsTestPalindromes test
     * */
    private static Stream<Arguments> setFieldsTestPalindromesArgs() {
        List<Arguments> listOfArguments = new ArrayList<>();
        listOfArguments.add(Arguments.of(new LinkedHashMap<String, Integer>() {{
            put("ala", 4);
            put("oko", 1);
        }}, "Ala, ala? ALA. ma oko! ALa"));
        listOfArguments.add(Arguments.of(new LinkedHashMap<String, Integer>() {
        }, "Santa Claus is coming to town?"));
        listOfArguments.add(Arguments.of(new LinkedHashMap<String, Integer>() {
        }, ". !!! ? () ::"));
        listOfArguments.add(Arguments.of(new LinkedHashMap<String, Integer>() {
        }, ""));
        listOfArguments.add(Arguments.of(new LinkedHashMap<String, Integer>() {{
            put("ala", 2);
            put("oko", 1);
            put("kok", 1);
            put("kajak", 1);
        }}, "Kok ma Ala i oko Ala też ma! No i kajak!"));
        return listOfArguments.stream();
    }

    /**Parametrized test for checking correctness of assigning diacritical marks
     * @param diacritics expected map of diacritics
     * @param input exemplary input strings
     * */
    @ParameterizedTest
    @MethodSource("setFieldsTestDiacriticsArgs")
    void setFieldsTestDiacritics(TreeMap<Character, Integer> diacritics, String input) {
        text.setFields(input);
        assertEquals(diacritics, text.getDiacritics());
    }
    /** Arguments for setFieldsTestDiacritics test
     * @return stream of arguments to setFieldsTestDiacritics test
     *  */
    private static Stream<Arguments> setFieldsTestDiacriticsArgs() {
        List<Arguments> listOfArguments = new ArrayList<>();
        listOfArguments.add(Arguments.of(new TreeMap<Character, Integer>() {
        }, "Santa Claus is coming to town? !!! ? () ::"));
        listOfArguments.add(Arguments.of(new TreeMap<Character, Integer>() {{
            put('ę', 4);
            put('ś', 2);
            put('ą', 4);
            put('ł', 1);
            put('ć', 1);
            put('ż', 2);
            put('ó', 1);
        }}, "Idę leśną dróżką na piękną łąkę i na ścieżce widzę ćme!"));
        listOfArguments.add(Arguments.of(new TreeMap<Character, Integer>() {{
            put('ß', 1);
            put('œ', 1);
        }}, "œß"));
        listOfArguments.add(Arguments.of(new TreeMap<Character, Integer>() {
        }, ""));
        return listOfArguments.stream();
    }

    /**Parametrized test for checking correctness of assigning number of words with proper number of letters
     * @param letters expected map of letters
     * @param input exemplary input strings
     * */
    @ParameterizedTest
    @MethodSource("setFieldsTestLettersArgs")
    void setFieldsTestLetters(TreeMap<Integer, Integer> letters, String input) {
        if (!input.isBlank()) {
            text.setFields(input);
            assertEquals(letters, text.getLetters());
        }
    }

    /** Arguments for setFieldsTestLetters test
     * @return stream of arguments to setFieldsTestLetters test
     * */
    private static Stream<Arguments> setFieldsTestLettersArgs() {
        List<Arguments> listOfArguments = new ArrayList<>();
        listOfArguments.add(Arguments.of(new TreeMap<Integer, Integer>() {{
            put(2, 2);
            put(4, 1);
            put(6, 1);
            put(5, 2);
        }}, "Santa Claus is coming to town? !!! ? () ::"));
        listOfArguments.add(Arguments.of(new TreeMap<Integer, Integer>() {
        }, "? !!! ? () ::"));
        listOfArguments.add(Arguments.of(new TreeMap<Integer, Integer>() {
        }, ""));
        listOfArguments.add(Arguments.of(new TreeMap<Integer, Integer>() {{
            put(1, 1);
            put(12, 1);
            put(5, 3);
            put(6, 1);
            put(3, 1);
        }}, "Czarno-biały? (kot) !idzie! :ulicą i, macha ogonem!"));
        return listOfArguments.stream();
    }

    /**Parametrized test for checking correctness of countLastChar method
     * @param expectedNumber expected number of last char in text
     * @param input exemplary input strings
     * */
    @ParameterizedTest
    @MethodSource("countLastCharArgs")
    void countLastChar(String input, int expectedNumber) {
        text.setFields(input);
        assertEquals(expectedNumber, text.countLastChar());
    }

    /** Arguments for countLastChar test
     * @return stream of arguments to countLastChar test
     **/
    private static Stream<Arguments> countLastCharArgs() {
        return Stream.of(
                Arguments.of("text", 2),
                Arguments.of("kitty cat", 3),
                Arguments.of("Santa Claus is coming to town?", 1),
                Arguments.of("Ala? Czy masz koteła? ! ?(Hmm. Hmm", 5),
                Arguments.of(".", 1),
                Arguments.of(". !!! ? () ::", 2)
        );
    }
}
