package com.company.view;

import com.company.model.Model;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Class, which handles printing out information to GUI - equivalent of previous View class.
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
public class FXMenuController {

    public Label lastCharLabel;
    public Label textInfoLabel;
    public Tab palindromesTab;
    public Tab diacriticsTab;
    public Label wordsInfoLabel;
    public Label palindromesLabel;
    public Label diacriticsLabel;
    public TableView<Map.Entry<String, Integer>> palindromesTableView;
    public TableColumn<Map.Entry<String, Integer>,String> columnP1;
    public TableColumn<Map.Entry<String, Integer>,Integer> columnP2;
    public TableColumn<Map.Entry<String, Integer>,String> columnP3;
    public TableColumn<Map.Entry<String, Integer>,String> columnP4;
    public TableView<Map.Entry<Character, Integer>> diacriticsTableView;
    public TableColumn<Map.Entry<Character, Integer>,Character> columnD1;
    public TableColumn<Map.Entry<Character, Integer>,Integer> columnD2;
    public TableColumn<Map.Entry<Character, Integer>,String> columnD3;
    public TableColumn<Map.Entry<Character, Integer>,String> columnD4;
    public TableView<Map.Entry<Integer, Integer>> wordsTableView;
    public TableColumn<Map.Entry<Integer, Integer>, Integer> columnW1;
    public TableColumn<Map.Entry<Integer, Integer>, String> columnW2;
    public TableColumn<Map.Entry<Integer, Integer>, String> columnW3;

    Model text = new Model();

    /**
     * Public method for initializing object of Text class with string given by user
     * @param string string typed in by user
     * */
    public void textInit(String string) {
        try {
            if (string.isBlank() || string.isEmpty())
                throw new NoInputTextException();
            else {
                text.setFields(string);
            }
        } catch (NoInputTextException e) {
            exception(e.getMessage());
        }

    }

    /**
     * Public method for handling newButton click - changing stage to start one and clearing all text fields.
     * @param event type of ActionEvent used to change stage
     * @throws IOException if fxml won't be found in resources
     * */
    public void newClicked(ActionEvent event) throws IOException {
        text.clear();
        Parent start = FXMLLoader.load(getClass().getResource("../controller/startWindow.fxml"));
        Stage menuWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menuWindow.setTitle("My Program");
        menuWindow.setScene(new Scene(start, 500, 350));
        menuWindow.show();
    }

    /**
     * Public method initializing all tabs in this stage and running other methods of this class to do this.
     * If there is no palindromes nor diacritics those tabs are being disabled and proper message is
     * added as tooltip. The equivalent of this method in View class was printAll.
     * */
    public void initialize() { //=printAll
        infoTextInit();
        lastCharInit();
        wordsInfoInit();
        if (text.getPalindromes().isEmpty()) {
            palindromesTab.setDisable(true);
            Tooltip ptt = new Tooltip("There is no palindromes!");
            palindromesTab.setTooltip(ptt);
        } else palindromesInit();
        if (text.getDiacritics().isEmpty()) {
            diacriticsTab.setDisable(true);
            Tooltip dtt = new Tooltip("There is no diacritical marks!");
            diacriticsTab.setTooltip(dtt);
        } else diacriticsInit();
    }

    /**
     * Private method displaying information about text: string, words, number of chars and number of words.
     * Information is being displayed on textInfoLabel in proper tab. The equivalent of this method in View
     * class was printTextInfo. */
    private void infoTextInit() {//=printTextInfo
        StringBuilder infoText = new StringBuilder(" String: " + text.getString() +
                "\n Words: ");
        for (String l : text.getWords()) {
            infoText.append(l).append(", ");
        }
        infoText.append("\n Number of chars: ").append(text.getNumberOfChars()).append("\n Number of words: ").append(text.getNumberOfWords());

        textInfoLabel.setText(infoText.toString());
    }

    /**
     * Private method displaying information about the last character of given text. It displays information
     * about the number of times the last character is repeated in this text, along with information about the
     * percentage of these letters. Information is being displayed on proper label in proper tab. The equivalent
     * of this method in View class was printLastChar(text).
     * */
    private void lastCharInit() {//=printLastChar(text);
        //implementation of lambda expression
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        StringBuilder lastChar = new StringBuilder( " Last char, which is: " + text.getString().charAt(text.getString().length() - 1) + ", ");
        String s;
        if (text.countLastChar() > 1) {
            s = "s. ";
        } else s = ". ";
        lastChar.append("occurs ").append(text.countLastChar()).append(" time").append(s).append("\nIt is ").append(divide(text.countLastChar(), text.getNumberOfChars(), division)).append("% of all chars.");
        lastCharLabel.setText(lastChar.toString());
    }

    /**
     * Private method displaying information about number of words in given text as well as displaying information
     * on how many words of a certain length are present in the text with a percentage value. Information is being
     * displayed on proper label in proper tab and statistics are given in TableView. The equivalent of this method
     * in View class was printWordsInfo.*/
    private void wordsInfoInit() {//=printWordsInfo(text);
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        //Label
        StringBuilder wordsInfo = new StringBuilder(" Number of words: ").append(text.getNumberOfWords());
        String s;
        if (text.getNumberOfWords() > 1) {
            s = "are:";
        } else s = "is:";
        wordsInfo.append("\n In the text there ").append(s);
        wordsInfoLabel.setText(wordsInfo.toString());

        //TableView
        final ObservableList<Map.Entry<Integer, Integer>> letters = FXCollections.observableArrayList(text.getLetters().entrySet());
        columnW2.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey() + "-letter word"));
        columnW1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));
        columnW3.setCellValueFactory(p -> new SimpleObjectProperty<>(divide(p.getValue().getValue(), text.getNumberOfWords(), division) + " %"));
        wordsTableView.setItems(letters);

    }

    /** Private method displaying information about palindromes. It counts and displays information about the number of
     * palindromes; if a given palindrome occurs more than 1 time, provide occurrence statistics for all such palindromes.
     * As a extension to functionality also palindromes, which occur 1 time are printed out. Information is being
     * displayed on proper label in proper tab and statistics are given in TableView. The equivalent of this method
     * in View class was printPalindromesInfo.*/
    private void palindromesInit() {//=printPalindromesInfo(text);
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        //Label
        Set<String> setKeyPalindromes = text.getPalindromes().keySet();
        int count = 0;
        for (String l : setKeyPalindromes) {
            count += text.getPalindromes().get(l);
        }
        String palindromesInfo = " Number of palindromes: " + count +
                "\n It is " + setPrecision(((double) count * 100) / text.getNumberOfWords()) + "% of all words.";
        palindromesLabel.setText(palindromesInfo);

        //TableView
        final ObservableList<Map.Entry<String, Integer>> palindromes = FXCollections.observableArrayList(text.getPalindromes().entrySet());
        int finalCount = count;
        columnP1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        columnP2.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));
        columnP3.setCellValueFactory(p -> new SimpleObjectProperty<>(divide(p.getValue().getValue(), finalCount, division) + " %"));
        columnP4.setCellValueFactory(p -> new SimpleObjectProperty<>(divide(p.getValue().getValue(), text.getNumberOfWords(), division) + " %"));
        palindromesTableView.setItems(palindromes);
    }

    /**
     * Private method displaying information about diacritical marks on proper label in proper tab.
     * It displays in TableView number of diacritics along with information about the percentage of each
     * diacritics in the text. The equivalent of this method in View class was printDiacriticsInfo. */
    private void diacriticsInit() {//=printDiacriticsInfo(text);
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);

        //Label
        Set<Character> setKeyDiacritics = text.getDiacritics().keySet();
        int count = 0;
        for (Character l : setKeyDiacritics) {
            count += text.getDiacritics().get(l);
        }
        String diacriticsInfo = " Number of diacritical marks: " + count +
                "\n It is " + divide(count, text.getNumberOfChars(), division) + "% of all chars.";
        diacriticsLabel.setText(diacriticsInfo);
        //TableView
        final ObservableList<Map.Entry<Character, Integer>> diacritics = FXCollections.observableArrayList(text.getDiacritics().entrySet());
        columnD1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        columnD2.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));
        int finalCount = count;
        columnD3.setCellValueFactory(p -> new SimpleObjectProperty<>(divide(p.getValue().getValue(), finalCount, division) + " %"));
        columnD4.setCellValueFactory(p -> new SimpleObjectProperty<>(divide(p.getValue().getValue(), text.getNumberOfChars(), division) + " %"));
        diacriticsTableView.setItems(diacritics);
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

    /**
     * Public method printing out exception message when there is no input text
     * @param s message to print out
     */
    public void exception(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Ups! Exception!");
        alert.setContentText(s);
        alert.showAndWait();
    }

}


