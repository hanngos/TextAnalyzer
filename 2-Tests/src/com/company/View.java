package com.company;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.AnnotatedElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;


/**
 * Class printing out information to Console.
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
public class View {

    /**
     * Public method printing out information about program: "TEXT MINING PROGRAM".
     */
    public void printInfo() {
        AnnotatedElement element = Main.class;
        if (element.isAnnotationPresent(MyProgramInfo.class)) {
            MyProgramInfo info = element.getAnnotation(MyProgramInfo.class);
            System.out.println(info.comment());
            System.out.println("name: " + info.name());
            System.out.println("author: " + info.author());
        }

        System.out.println("------------------------");
    }

    /**
     * Public method printing out all information about analyzed text by calling other private methods of this class
     *
     * @param text an object of Text class used for calling other methods of View class
     */
    public void printAll(@NotNull Text text) {
        printTextInfo(text);
        printLastChar(text);
        printWordsInfo(text);
        printPalindromesInfo(text);
        printDiacriticsInfo(text);
    }

    /**
     * Private method printing out information about text: string, words, number of chars and number of words.
     *
     * @param text an object of Text class used for running the Text class methods: getString, getNumberOfChars,
     *             getNumberOfWords giving access to data stored in private fields of Text class.
     */
    private void printTextInfo(Text text) {
        System.out.println("---TEXT INFO------------");
        System.out.println(" String: " + text.getString());
        System.out.print(" Words: ");
        text.getWords().forEach(p -> System.out.print(p + " "));
        /*System.out.print(" Words: ");
        for (String l : text.getWords()){
            System.out.print(l + " ");
        }*/
        System.out.println();
        System.out.println(" Number of chars: " + text.getNumberOfChars());
        System.out.println(" Number of words: " + text.getNumberOfWords());
    }

    /**
     * Private method printing out information about the last character of given text. It displays information
     * about the number of times the last character is repeated in this text, along with information about the
     * percentage of these letters. It is calling setPrecision method to round percentage value to two decimal places.
     *
     * @param text an object of Text class used for running the Text class methods: getString, countLastChar and
     *             getNumberOfChars giving access to data stored in private fields of Text class.
     */
    private void printLastChar(Text text) {
        System.out.println("---LAST CHARACTER-------");
        System.out.print(" Last char, which is: " + text.getString().charAt(text.getString().length() - 1) + ", ");
        String s;
        if (text.countLastChar() > 1) {
            s = "s. ";
        } else s = ". ";
        System.out.println("occurs " + text.countLastChar() + " time" + s + "It is " + divide(text.countLastChar(), text.getNumberOfChars(), division) /*setPrecision((double)text.countLastChar() * 100 / text.getNumberOfChars())*/ + "% of all chars.");
    }

    /**
     * Private method printing out information about number of words in given text as well as displaying information
     * on how many words of a certain length are present in the text with a percentage value. It is calling setPrecision
     * method to round percentage value to two decimal places.
     *
     * @param text an object of Text class used for running the Text class methods: getLetters, countLastChar and
     *             getNumberOfWords giving access to data stored in private fields of Text class.
     */
    private void printWordsInfo(Text text) {
        System.out.println("---WORDS INFO-----------");
        System.out.println(" Number of words: " + text.getNumberOfWords());
        Set<Integer> setKeyLetters = text.getLetters().keySet();
        String s;
        if (text.getNumberOfWords() > 1) {
            s = "are:";
        } else s = "is:";
        System.out.println(" In the text there " + s);
        for (Integer l : setKeyLetters) {
            String s1;
            if (text.getLetters().get(l) > 1) {
                s1 = "s. ";
            } else s1 = ". ";
            System.out.println("    " + text.getLetters().get(l) + " " + l + "-letter word" + s1 + "It is " + divide(text.getLetters().get(l), text.getNumberOfWords(), division)/*setPrecision((double)text.getLetters().get(l) * 100 / text.getNumberOfWords())*/ + "% of all words.");
        }
    }

    /**
     * Private method printing out information about palindromes. It counts and displays information about the number of
     * palindromes; if a given palindrome occurs more than 1 time, provide occurrence statistics for all such palindromes.
     * As a extension to functionality also palindromes, which occur 1 time are printed out. It is calling setPrecision
     * method to round percentage value to two decimal places.
     *
     * @param text an object of Text class used for running the Text class methods: getPalindromes giving access to data
     *             stored in private field palindromes of Text class, which type is Map(String, Integer).
     */
    private void printPalindromesInfo(Text text) {
        System.out.println("---PALINDROMES----------");
        if (text.getPalindromes().isEmpty()) {
            System.out.println(" There is no palindromes!");
        } else {
            Set<String> setKeyPalindromes = text.getPalindromes().keySet();
            int count = 0;
            for (String l : setKeyPalindromes) {
                count += text.getPalindromes().get(l);
            }
            System.out.println(" Number of palindromes: " + count);
            System.out.println(" It is " + setPrecision(((double) count * 100) / text.getNumberOfWords()) + "% of all words.");
            for (String l : setKeyPalindromes) {
                if (text.getPalindromes().get(l) > 1) {
                    System.out.println("    " + l + " occurs " + text.getPalindromes().get(l) + " times. It is " + divide(text.getPalindromes().get(l), count, division)/*setPrecision(((double)text.getPalindromes().get(l) * 100) / count)*/ + "% of all palindromes and " + divide(text.getPalindromes().get(l), text.getNumberOfWords(), division) /* setPrecision(((double)text.getPalindromes().get(l) * 100) / text.getNumberOfWords())*/ + "% of all words.");
                } else {
                    System.out.println("    * " + l + " occurs 1 time."); //functionality extension
                }
            }
        }
    }

    /**
     * Private method printing out information about diacritical marks. It displays number of diacritics along with
     * information about the percentage of each diacritics in the text. It is calling setPrecision method to round
     * percentage value to two decimal places.
     *
     * @param text an object of Text class used for running the Text class methods: getDiacritics and getNumberOfChars
     *             giving access to data stored in private fields of Text class.
     */
    private void printDiacriticsInfo(Text text) {
        System.out.println("---DIACRITICS-----------");
        if (text.getDiacritics().isEmpty()) {
            System.out.println(" There is no diacritical marks!");
        } else {
            Set<Character> setKeyDiacritics = text.getDiacritics().keySet();
            int count = 0;
            for (Character l : setKeyDiacritics) {
                count += text.getDiacritics().get(l);
            }
            System.out.println(" Number of diacritical marks: " + count);
            System.out.println(" It is " + divide(count, text.getNumberOfChars(), division)/*setPrecision(((double) count * 100) / text.getNumberOfChars())*/ + "% of all chars.");
            for (Character l : setKeyDiacritics) {
                String s;
                if (text.getDiacritics().get(l) > 1) {
                    s = "s";
                } else {
                    s = "";
                }
                System.out.println("    " + l + " occurs " + text.getDiacritics().get(l) + " time" + s + ". It is " + divide(text.getDiacritics().get(l), count, division)/*setPrecision(((double) text.getDiacritics().get(l) * 100) / count)*/ + "% of all diacritics and " + divide(text.getDiacritics().get(l), text.getNumberOfChars(), division)/*setPrecision(((double) text.getDiacritics().get(l) * 100) / text.getNumberOfChars())*/ + "% of all chars.");
            }
        }
    }

    /**
     * Public method printing out exception message when there is no input text
     * @param s message to print out
     */
    public void exception(String s) {
        System.out.println(s);
    }

    /**
     * Private method setting precision of given value to two decimal places.
     *
     * @param value double type value that is going to be rounded
     * @return rounded value
     */
    private double setPrecision(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /** Specification of the lambda expression with two parameters using interface
     * */
    interface Statistics {
        double operation(int a, int b);
    }

    /**Private method with lambda expression as a parameter
     * @param a dividend
     * @param b divisor
     * @param op lambda expression
     * @return result of operation
     * */
    private double divide(int a, int b, Statistics op) {
        return op.operation(a, b);
    }

    /**Implementations of the lambda expressions
     * */
    Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);

}
