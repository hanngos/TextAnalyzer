package com.company.view;

/**
 * Exception class for no input text given by user.
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class NoInputTextException extends Exception {
    /**
     * A private field holding message to be displayed when NoInputException was thrown
     */
    private final String message;

    /**
     * Non-parameter constructor
     */
    public NoInputTextException() {
        this.message = "Exception! No text was typed in!";
    }

    /**
     * Public method enabling getting data stored in message field.
     * @return - message of exception
     */
    public String getMessage(){
        return this.message;
    }
}
