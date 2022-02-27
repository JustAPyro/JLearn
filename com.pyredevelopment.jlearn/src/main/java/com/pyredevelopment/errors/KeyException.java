package com.pyredevelopment.errors;

import java.util.HashMap;

/**
 * Error class for KeyException
 * TODO: Add information here, refer to https://pandas.pydata.org/docs/reference/api/pandas.DataFrame.at.html#pandas.DataFrame.at
 */
public class KeyException extends RuntimeException {

    public KeyException() {
        super("You tried to access a key that wasn't in this data.");
    }

}
