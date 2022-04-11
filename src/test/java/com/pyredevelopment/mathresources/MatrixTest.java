package com.pyredevelopment.mathresources;

import com.pyredevelopment.Dataset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {


    @Test
    @DisplayName("Testing Matrix Determinants")
    void determinant() {

        double[][] testMatrix;
        double expected;
        double result;


        /* - - - - - Testing Throws Error for non-square determinant - - - - - */
        // TODO: Testing Throws Error for non-square determinant

        /* - - - - - Testing 1x1 Matrix Determinant - - - - - */

        // Setup
        expected = 5.0;
        testMatrix = new double[][] {{-5}};

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 2x2 Matrix Determinant - - - - - */

        // Setup
        expected = -3.0;
        testMatrix = new double[][] {
                {7, 5},
                {2, 1}
        };

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 3x3 Matrix Determinant - - - - - */

        // Setup
        expected = -48.0;
        testMatrix = new double[][] {
                {6, 3, 4},
                {6, 1, 8},
                {3, 0, 9}
        };

        // Execute
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 4x4 Matrix Determinant - - - - - */

        // Setup
        expected = -288.0;
        testMatrix = new double[][] {
                {3, 8, 8, 4},
                {3, 7, 6, 1},
                {5, 7, 6, 9},
                {7, 9, 2, 9}
        };

        // Execute
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 4x4 Matrix Determinant with floats - - - - - */
        // TODO: Testing 4x4 Matrix Determinant with floats

        /* - - - - - Testing a 10x10 Matrix Determinant - - - - - */

        // Setup
        expected = -1075387108;
        testMatrix = new double[][] {
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
        };

        // Execute
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Testing Matrix Cofactor")
    void cofactor() {

        double[][] test;
        double[][] expected;
        double[][] result;

        /* - - - - - Testing cofactor of a 3x3 matrix - - - - - */

        // Setup
        test = new double[][]{
                {1, 9, 3},
                {2, 5, 4},
                {3, 7, 8}
        };
        expected = new double[][]{
                {12, -4, -1},
                {-51, -1, 20},
                {21, 2, -13}
        };

        // Execute
        result = Matrix.cofactor(test);

        // assert that each row is correct
        for (int i = 0; i < expected.length; i++)
            assertArrayEquals(expected[i], result[i]);

    }

    @Test
    @DisplayName("Testing Transposing a Matrix")
    void transpose() {

        double[][] test;
        double[][] result;
        double[][] expected;

        /* - - - - - Testing transposing a 2x5 matrix - - - - - */

        // Setup
        test = new double[][] {
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        };
        expected = new double[][] {
                {0, 5},
                {1, 6},
                {2, 7},
                {3, 8},
                {4, 9}
        };

        // Execute
        result = Matrix.transpose(test);

        // Assert that each row is correct
        for (int i = 0; i < expected.length; i++)
            assertArrayEquals(expected[i], result[i]);

        /* - - - - - Testing transposing a square matrix - - - - - */

        // Setup
        test = new double[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        expected = new double[][] {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        // Execute
        result = Matrix.transpose(test);

        // Assert that each row is correct
        for (int i = 0; i < expected.length; i++)
            assertArrayEquals(expected[i], result[i]);

    }

    @Test
    @DisplayName("Testing matrix multiplication by constant")
    void multiply_constant() {

        double[][] testMatrix;
        double testConstant;

        double[][] expected;
        double[][] result;

        /* - - - - - Testing Multiplication on a 3x3 - - - - - */

        // Setup
        testConstant = 2.0;
        testMatrix = new double[][]{
                {-10, -10, -10},
                {3, 3, 3},
                {2.5, 2.5, 2.5}
        };
        expected = new double[][] {
                {-20, -20, -20},
                {6, 6, 6},
                {5, 5, 5}
        };

        // Execute
        result = Matrix.multiply(testMatrix, testConstant);

        // Assert that each row is correct
        for (int i = 0; i < expected.length; i++)
            assertArrayEquals(expected[i], result[i]);



    }

}