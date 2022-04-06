package com.pyredevelopment.mathresources;

import com.pyredevelopment.Dataset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {


    @Test
    @DisplayName("Testing Matrix Determinants")
    void determinant() {

        Dataset testMatrix;
        double expected;
        double result;


        /* - - - - - Testing Throws Error for non-square determinant - - - - - */
        // TODO: Testing Throws Error for non-square determinant

        /* - - - - - Testing 1x1 Matrix Determinant - - - - - */

        // Set up
        expected = -5.0;
        testMatrix = new Dataset(new double[][] {{5}});

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 2x2 Matrix Determinant - - - - - */

        // Setup
        expected = -3.0;
        testMatrix = new Dataset(new double[][] {
                {7, 5},
                {2, 1}
        });

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 3x3 Matrix Determinant - - - - - */

        // Setup
        expected = -48.0;
        testMatrix = new Dataset(new double[][] {
                {6, 3, 4},
                {6, 1, 8},
                {3, 0, 9}
        });

        // Execute
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 4x4 Matrix Determinant - - - - - */

        // Setup
        expected = -288.0;
        testMatrix = new Dataset(new double[][] {
                {3, 8, 8, 4},
                {3, 7, 6, 1},
                {5, 7, 6, 9},
                {7, 9, 2, 9}
        });

        // Execute
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 4x4 Matrix Determinant with floats - - - - - */
        // TODO: Testing 4x4 Matrix Determinant with floats

        /* - - - - - Testing a 10x10 Matrix Determinant - - - - - */

        // Setup
        expected = -1075387108;
        testMatrix = new Dataset(new double[][] {
                {8,8,8,9,3,7,3,8,8,4},
                {7,2,5,7,4,3,6,9,3,9},
                {9,0,0,2,3,4,0,1,8,8},
                {4,4,6,8,3,1,8,0,0,1},
                {7,8,0,5,2,7,1,9,4,0},
                {4,1,5,7,5,9,9,0,3,2},
                {4,4,2,7,8,1,5,6,1,4},
                {4,5,5,0,8,9,5,3,0,9},
                {5,7,1,7,5,4,2,0,0,1},
                {1,7,2,1,5,2,1,4,7,8}
        });

        // Execute
        expected = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);


    }


}