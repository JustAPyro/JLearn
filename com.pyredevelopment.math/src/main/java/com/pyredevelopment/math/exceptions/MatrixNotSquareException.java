package com.pyredevelopment.math.exceptions;

/**
 * This error is thrown when matrix is not square
 * TODO: Add extra docs
 */
public class MatrixNotSquareException extends RuntimeException{

    public MatrixNotSquareException() {
        super("Tried to perform a function that requires a square matrix on a non-square matrix");
    }

}
