package com.pyredevelopment.math.exceptions;

/**
 * This error is thrown when matrix is not square
 * TODO: Add extra docs
 */
public class MatrixNotSquareException extends RuntimeException{

    public MatrixNotSquareException(String function) {
        super("Tried to perform " + function + " on a non-square matrix.\n" +
                function + " requires a square matrix");
    }

}
