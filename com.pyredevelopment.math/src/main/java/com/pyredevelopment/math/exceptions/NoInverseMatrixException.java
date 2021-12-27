package com.pyredevelopment.math.exceptions;

/**
 * This inverse is thrown from the math package whenever a matrix inverse
 * that does not exist attempts to be computed.
 */
public class NoInverseMatrixException extends RuntimeException {

    /**
     * This just calls the RuntimeException with a specific error message.
     * **/
    public NoInverseMatrixException() {
        super("Tried to calculate the inverse on a matrix that has none.");
    }

}
