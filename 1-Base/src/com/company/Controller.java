package com.company;

import java.util.Scanner;
/**
 * Class for reading data typed by user
 *
 *  @author Hanna Gościniak
 *  @version 1.0
 */
public class Controller {
    /**
     * Private field storing the string given by user to be analyzed in program.
     */
    private String string;
    /**
     * Public method setting the data (text to be analyzed), typed by the user, in the string field, which is private.
     */
    public void setStringFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in text to be analyzed:");
        this.string = scanner.nextLine();
    }
    /**
     * Public method enabling getting the String typed by user, stored in the private string field.
     * @return - value of string field
     * */
    public String getString(){ return this.string; }
}

