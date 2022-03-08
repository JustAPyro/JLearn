package com.pyredevelopment.data;

/**
 * TODO: Document better
 * Custom exception class for data object extension classes.
 */
public class DataObjectException extends RuntimeException {

    public static final String FILE_NOT_FOUND = "Tried to load DataObject from a file that could not be found.";

    public DataObjectException(String message) {
        super(message);
    }
}
