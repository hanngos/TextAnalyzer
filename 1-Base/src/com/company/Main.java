package com.company;

/**
 * Class for running main method of the program used to analyze the text given by the user (text mining).
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
public class Main {
    /**
     * Public static method of main class. In this method objects of Text - M, Controller - C and View - V classes are created
     * and proper methods are called for them.
     * @param args is a table of command-line arguments.
     * */
    public static void main(String[] args) {
        Controller controller = new Controller();
        Text text = new Text();
        View view = new View();

        view.printInfo();
        controller.setStringFromUser();

        text.setFields(controller.getString(), view);

        if(text.getString() != null) {
            view.printAll(text);
        }
    }
}